package com.xzl.common.enums;

/**
 * @author leihfei
 * @description 数据库关键字匹配条件枚举
 * @email:leihfein@gmail.com
 * @since 21:14 2018/9/4
 */
public enum Operator {
    /**
     * 等于
     */
    EQ,
    /**
     * 模糊查询
     */
    LIKE,
    /**
     * 大于
     */
    GT,
    /**
     * 小于
     */
    LT,
    /**
     * 大于等于
     */
    GTE,
    /**
     * 小于等于
     */
    LTE,
    /**
     * 在列表中
     */
    IN,
    /**
     * 左模糊
     */
    LLIKE,
    /**
     * 右模糊
     */
    RLIKE,
    /**
     * 是空
     */
    ISNULL,
    /**
     * 两者之间
     */
    BETWEEN,
    /**
     * 不等于
     */
    NOTEQ,
    /**
     * 不是空
     */
    ISNOTNULL,
    /**
     * 不在列表中
     */
    NOTIN;
}
