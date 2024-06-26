package com.xzl.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName : NgPager
 * Package : com.xzl.common.model
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 14:39
 * @Version: jdk 1.8
 */
@Data
@ApiModel(value = "分页对象")
public class NgPager {

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
//    /**
//     * 排序字段
//     */
//    @ApiModelProperty(value = "排序字段名称")
//    private String sortField;
//    /**
//     * 1 asc or -1 desc
//     */
//    @ApiModelProperty(value = "排序规则，1-升序，-1降序")
//    private int sortOrder;
//    /**
//     * 多项排序条件
//     */
//    @ApiModelProperty(value = "多条件排序")
//    private SortMeta[] multiSortMeta;
    /**
     * 查询条件
     */
    @ApiModelProperty(value = "过滤条件")
    private Map<String, String> filters = new HashMap<>();

//    /**
//     * 全局搜索字段
//     */
//    private Object globalFilter;

}
