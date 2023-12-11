package com.xzl.entity.Pager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName : NoPage
 * Package : com.xzl.entity.Page
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/27 - 20:58
 * @Version: jdk 1.8
 */
@Data
public class Pager {
    /**
     * 分页起始个数
     */
    @ApiModelProperty(value = "起始记录数")
    private int first = 0;
    /**
     * 分页的个数
     */
    @ApiModelProperty(value = "分页的个数")
    private int rows = 10;
    /**
     * 负责人
     */
    private String inChargePerson;
    /**
     * 实训室
     */
    private String name;
    /**
     *  开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDateTime;
    /**
     *  结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDateTime;
}
