package com.xzl.service.Impl;


import com.xzl.common.model.NgPager;
import com.xzl.dao.*;
import com.xzl.entity.*;
import com.xzl.entity.Pager.Pager;
import com.xzl.entity.vo.DataVO;
import com.xzl.service.SysRoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import java.util.stream.Collectors;

/**
 * ClassName : SysRoomServiceImpl
 * Package : com.xzl.service.Impl
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 10:47
 * @Version: jdk 1.8
 */
@Service
public class SysRoomServiceImpl implements SysRoomService {
    @Autowired
    SysRoomDAO sysRoomDAO;
    @Autowired
    LotHkCameraDAO lotHkCameraDAO;
    @Autowired
    LotDiscriminateDAO lotDiscriminateDAO;

    @Autowired
    LotDiscriminateRoomConfigDAO discriminateRoomConfigDAO;
    @Autowired
    SysSectionDAO sysSectionDAO;
    @Autowired
    LotDiscriminateConfigDA0 discriminateConfigDA0;



    @Override
    public List<DataVO> list(Pager pager) {
        LocalTime s = LocalTime.now();
        List<DataVO> data = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(pager.getFirst(), pager.getRows());
        List<SysRoom> roomList = sysRoomDAO.findAll(buildRoomSpecification(pager), pageRequest).getContent();
        List<LotDiscriminate> lotDiscriminateList = null;
        if (!CollectionUtils.isEmpty(roomList)) {
            lotDiscriminateList = getLotDiscriminateList(roomList, pager);
        }

        List<SysSection> allTime = sysSectionDAO.findAll();
        List<SysSection> noonBreakTime = time(allTime);
        List<SysSection> attendeesTime = allTime.stream()
                .filter(sysSection -> !noonBreakTime.contains(sysSection))
                .collect(Collectors.toList());
        List<LotDiscriminateConfig> discriminateConfigList = discriminateConfigDA0.findAll();
        List<SysSection> totalRange = Collections.singletonList(
                new SysSection(LocalTime.of(0, 0), LocalTime.of(23, 59))
        );
        List<SysSection> oneMaxTime = CutTime(totalRange, 1);
        List<SysSection> newNoonBreakTime = CutTime(noonBreakTime, 1);
        List<SysSection> remainingRanges = findRemainingRanges(totalRange, allTime);
        List<SysSection> afterTime = CutTime(remainingRanges, 1);
        for (SysRoom sysRoom : roomList) {
            DataVO dataVO = new DataVO();
            BeanUtils.copyProperties(sysRoom, dataVO);
            Map<String, Integer> attendData = AGrandTotal(attendeesTime, lotDiscriminateList);
            Map<String, Integer> noonData = GrandTotal(newNoonBreakTime, lotDiscriminateList);
            Map<String, Integer> afterData = GrandTotal(afterTime, lotDiscriminateList);
            Map<String, Integer> oneMaxData = GrandTotal(oneMaxTime, lotDiscriminateList);
            int number = attendData.get("sumCount");
            double result = ((double) (number * 10) / 60);
            double v = get(result);
            dataVO.setSumTime(v);
            dataVO.setSectionTime(getSectionTime(sysRoom, discriminateConfigList));
            double v1 = get(dataVO.getSumTime() / dataVO.getSectionTime() * 100);
            dataVO.setUtilizationRate(v1);
            dataVO.setAttendeesNumber(attendData.get("sumPerson"));
            dataVO.setNoonBreakNumber(noonData.get("sumPerson"));
            dataVO.setAfterClassNumber(afterData.get("sumPerson"));
            dataVO.setUserNumber(dataVO.getAttendeesNumber() + dataVO.getNoonBreakNumber() + dataVO.getAfterClassNumber());
            dataVO.setSumUserNumber(oneMaxData.get("sumPerson"));
            double v2 = get((double) (attendData.get("MaxPerson") * 10) / 60);
            dataVO.setSumTimeNumber(v2);
            data.add(dataVO);
        }

        LocalTime e = LocalTime.now();
        Duration between = Duration.between(s, e);
        System.out.println(between);
        return data;
    }

    private static double get(double Data) {
        String SumTimeNumber = String.format("%.1f", Data);
        return Double.parseDouble(SumTimeNumber);
    }

    private Specification<SysRoom> buildRoomSpecification(Pager pager) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.and(); // 默认是 AND 连接词

            if (StringUtils.hasLength(pager.getInChargePerson())) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(root.get("inChargePerson"), "%" + pager.getInChargePerson() + "%")
                );
            }

            if (StringUtils.hasLength(pager.getName())) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(root.get("name"), "%" + pager.getName() + "%")
                );
            }

            return predicate;
        };
    }


    private List<LotDiscriminate> getLotDiscriminateList(List<SysRoom> roomList, Pager pager) {
        List<String> roomIds = roomList.stream().map(SysRoom::getId).collect(Collectors.toList());

        LocalDateTime start = pager.getStartDateTime().atStartOfDay();
        LocalDateTime end = pager.getEndDateTime().atStartOfDay().plusDays(1).minusNanos(1);
        Specification<LotDiscriminate> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.ge(root.get("personNum"), 1),
                        root.get("roomId").in(roomIds),
                        criteriaBuilder.greaterThanOrEqualTo(root.get("recordTime"), start),
                        criteriaBuilder.lessThanOrEqualTo(root.get("recordTime"), end)
                );
        return lotDiscriminateDAO.findAll(spec);
    }

    /**
     * 房间类型对应的类型
     * @param sysRoom 房间
     * @param discriminateConfigList 类型数据
     * @return 对应数据类型的课节时间
     */
    private double getSectionTime(SysRoom sysRoom, List<LotDiscriminateConfig> discriminateConfigList) {
        double sectionNumber = 0;

        for (LotDiscriminateConfig discriminateConfig : discriminateConfigList) {
            if (Objects.equals(discriminateConfig.getType(), sysRoom.getType())) {
                sectionNumber = discriminateConfig.getSectionNumber();  // 修正这一行
            }
        }

        return sectionNumber;
    }


    /**
     * 筛选出“中午”的所有数据
     * @param sysSectionList 课节的所有数据
     * @return 所有中午的数据
     */
    private List<SysSection> time(List<SysSection> sysSectionList) {
        return sysSectionList.stream().filter(d -> d.getSectionName().equals("中午")).collect(Collectors.toList());
    }


    /**
     *  将数据用时间筛选出时间段之间为连续的数据
     * @param timeList 传入所有时间段
     * @param lotDiscriminateList  数据
     * @return
     */
    private static Map<String, Integer> GrandTotal(List<SysSection> timeList, List<LotDiscriminate> lotDiscriminateList) {
        Map<String, Integer> data = new HashMap<>();
        int sumPerson = 0;

        for (SysSection sysSection : timeList) {
            Map<LocalDateTime, Integer> result = getLocalDateTimeIntegerMap(lotDiscriminateList);
            System.out.println(result);
            ArrayList<Integer> integers = new ArrayList<>(0);
            result.forEach((t, i) -> {
                if (isWithinTimeRange(sysSection.getStartTime(), sysSection.getEndTime(), t.toLocalTime())) {
                    integers.add(i);
                }
            });
            System.out.println(sysSection.getStartTime()+"---"+sysSection.getEndTime()+"---"+integers);

            if (!integers.isEmpty()) {
                Integer max = Collections.max(integers);

                sumPerson += max;
            }

        }

        data.put("sumPerson", sumPerson);
        return data;
    }

    /**
     * 过滤单次拍摄总和不大于2的数据
     * @param lotDiscriminateList 数据
     * @return 得到的是总和大于2的数据
     */
    private static Map<LocalDateTime, Integer> getLocalDateTimeIntegerMap(List<LotDiscriminate> lotDiscriminateList) {
        return lotDiscriminateList.stream()
                .collect(Collectors.groupingBy(
                        LotDiscriminate::getRecordTime,
                        Collectors.summingInt(LotDiscriminate::getPersonNum)
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 2)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     *  将数据用时间筛选出时间段之间为间隔的数据
     * @param timeList 传入所有时间段
     * @param lotDiscriminateList  数据
     * @return
     */
    private static Map<String, Integer> AGrandTotal(List<SysSection> timeList, List<LotDiscriminate> lotDiscriminateList) {
        Map<String, Integer> data = new HashMap<>();
        int sumPerson = 0;
        int sumCount = 0;
        int MaxPerson = 0;
        for (SysSection sysSection : timeList) {
            Map<LocalDateTime, Integer> result = getLocalDateTimeIntegerMap(lotDiscriminateList);

            System.out.println(result);
            ArrayList<Integer> integers = new ArrayList<>(0);
            result.forEach((t, i) -> {
                if (aWithinTimeRange(sysSection.getStartTime(), sysSection.getEndTime(), t.toLocalTime())) {
                    integers.add(i);
                }
            });
            System.out.println(integers);
            MaxPerson = result.values().stream().mapToInt(Integer::intValue).sum();
            if (!integers.isEmpty()) {
                Integer max = Collections.max(integers);
                if (max > 2) {
                    sumPerson += max;
                }
            }

            sumCount = result.size();
        }

        data.put("sumPerson", sumPerson);
        data.put("sumCount", sumCount);
        data.put("MaxPerson", MaxPerson);
        return data;
    }


    /**
     * 筛选出已知时间段（knowRanges）以外的时间段
     * @param totalRange 需要筛选的时间段
     * @param knownRanges 已知时间 段
     * @return 在(totalRange)范围内，筛选出knowRanges以外的时间段
     */
    public static List<SysSection> findRemainingRanges(List<SysSection> totalRange, List<SysSection> knownRanges) {
        List<SysSection> remainingRanges = new ArrayList<>(totalRange);

        for (SysSection knownRange : knownRanges) {
            List<SysSection> newRemainingRanges = new ArrayList<>();

            for (SysSection remainingRange : remainingRanges) {
                // 计算 remainingRange 和 knownRange 的交集
                LocalTime start = remainingRange.getStartTime().isAfter(knownRange.getStartTime())
                        ? remainingRange.getStartTime()
                        : knownRange.getStartTime();
                LocalTime end = remainingRange.getEndTime().isBefore(knownRange.getEndTime())
                        ? remainingRange.getEndTime()
                        : knownRange.getEndTime();

                // 如果有交集，添加新的剩余时间范围
                if (!start.isAfter(end)) {
                    if (!remainingRange.getStartTime().equals(start)) {
                        newRemainingRanges.add(new SysSection(remainingRange.getStartTime(), start));
                    }
                    if (!remainingRange.getEndTime().equals(end)) {
                        newRemainingRanges.add(new SysSection(end, remainingRange.getEndTime()));
                    }
                } else {
                    newRemainingRanges.add(remainingRange);
                }
            }

            remainingRanges = newRemainingRanges;
        }

        return remainingRanges;
    }


    /**
     * 判断时间间隔之间是否满足大于25分钟，满足的将其切割为指定时间大小的时间段
     * @param remainingRanges 需要判断的所有时间段
     * @param intervalMinutes 指定切割的时间大小
     * @return 满足条件下的所有切割后的时间
     */
    public static List<SysSection> CutTime(List<SysSection> remainingRanges, int intervalMinutes) {
        List<SysSection> sysSectionlist = new ArrayList<>();
        remainingRanges.forEach(sysSection -> {
            LocalTime startTime = sysSection.getStartTime();
            LocalTime endTime = sysSection.getEndTime();
            if (Duration.between(startTime, endTime).getSeconds() > 25 * 60) {
                sysSectionlist.add(new SysSection(startTime, endTime));
            }
        });
        ArrayList<SysSection> data = new ArrayList<>();
        sysSectionlist.forEach(sysSection -> {
            List<SysSection> sysSections = cutTime(sysSection.getStartTime(), sysSection.getEndTime(), intervalMinutes);
            data.addAll(sysSections);
        });
        return data;
    }

    /**
     * 时间段切割
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param intervalMinutes 切割的时间段的大小
     * @return 按指定时间段切割，不足时间段大小，最大时间段为最后时间
     */
    private static List<SysSection> cutTime(LocalTime startTime, LocalTime endTime, int intervalMinutes) {
        List<SysSection> data = new ArrayList<>();

        // 如果开始时间加上时间间隔小于结束时间，进行切割
        if (startTime.plusHours(intervalMinutes).isBefore(endTime)) {
            data.add(new SysSection(startTime, startTime.plusHours(intervalMinutes)));

            // 判断剩余的时间是否大于一个小时
            if (Duration.between(startTime.plusHours(intervalMinutes), endTime).getSeconds() > (long) intervalMinutes * 60 * 60) {
                // 递归调用并将结果添加到data中
                data.addAll(cutTime(startTime.plusHours(intervalMinutes), endTime, intervalMinutes));
            } else {
                data.add(new SysSection(startTime.plusHours(intervalMinutes), endTime));
            }
        }

        return data;
    }


    /**
     * 判断时间是否在指定范围内
     * @param startTime 开始时间（开区间）
     * @param endTime 结束时间（闭区间）
     * @param targetTime 判断时间
     * @return true 在指定范围，反之
     */
    public static boolean isWithinTimeRange(LocalTime startTime, LocalTime endTime, LocalTime targetTime) {
        return targetTime.isAfter(startTime) && !targetTime.isAfter(endTime) || targetTime.equals(endTime);
    }

    /**
     * 判断时间是否在指定范围内
     * @param startTime 开始时间(闭区间)
     * @param endTime 结束时间（闭区间）
     * @param targetTime 判断的时间
     * @return true 在范围内，反之
     */
    public static boolean aWithinTimeRange(LocalTime startTime, LocalTime endTime, LocalTime targetTime) {
        return !targetTime.isBefore(startTime) && !targetTime.isAfter(endTime) || targetTime.equals(endTime);
    }

}
