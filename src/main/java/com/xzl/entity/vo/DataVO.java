package com.xzl.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;

/**
 * ClassName : DataVO
 * Package : com.xzl.entity.vo
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 11:21
 * @Version: jdk 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataVO {
    /**
     * 房间号
     */
    private String number;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否共享是、否
     */
    private String shareStatus;

    /**
     * 负责人
     */
    private Long inChargePerson;
    /**
     * 实际使用时长：该频次数*10分钟/60；
     */
    @Digits(integer = 5, fraction = 1)
    private Double sumTime;
    /**
     * 课节时长
     */
    private Double sectionTime;
    /**
     * 利用率
     */
    private Double utilizationRate;
    /**
     * 上课人次数
     */
    private Integer attendeesNumber;
    /**
     * 午休人次数
     */
    private Integer noonBreakNumber;
    /**
     * 课后人次数
     */
    private Integer  afterClassNumber;
    /**
     * 使用人次数：等于上课、午休、午休相加
     */
    private Integer userNumber;
    /**
     * 使用总人时（最大）：每1个小时取最大人数累加
     */
    private Integer sumUserNumber;
    /**
     * 使用总人时（累加）：人数相加值*10/60
     */
    private Double sumTimeNumber;


}
