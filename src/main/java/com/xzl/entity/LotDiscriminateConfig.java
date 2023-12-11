package com.xzl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * ClassName : LotDiscriminateConfig
 * Package : com.xzl.entity
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 10:34
 * @Version: jdk 1.8
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lot_discriminate_config")
public class LotDiscriminateConfig implements Serializable {
    @Id
    private String id;
    /**
     * 房间类型,参照字典中的room类型
     */
    private Integer type;
    /**
     * 房间类型,参照字典中的room类型
     */
    private String typeName;
    /**
     * 课节人数
     */
    private String personNumber;
    /**
     * 课节
     */
    private double sectionNumber;
    private String weekDay;
    private String yearWeek;


}
