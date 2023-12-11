package com.xzl.common.model;


import com.xzl.common.enums.WhereOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * @author leihfei
 * @description 分页过滤条件
 * @since 20:56 2018/9/4
 * @email:leihfein@gmail.com
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "过滤条件构造对象")
public class NgFilter {
    /**
     * 过滤的值
     */
    @ApiModelProperty(value = "过滤的值")
    private String value;  //角色id

    /**
     * 数据库匹配类型
     * in ,<> =.....
     */
    @ApiModelProperty(value = "过滤匹配模式")
    private String matchMode;

    @ApiModelProperty(value = "字段之间使用连接方式,and/or")
    private WhereOperator whereType = WhereOperator.AND;

    public NgFilter(String value, String matchMode) {
        this.value = value;
        this.matchMode = matchMode;
        this.whereType = WhereOperator.AND;
    }
}
