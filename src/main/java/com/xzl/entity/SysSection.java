package com.xzl.entity;

import com.xzl.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * ClassName : Syssection
 * Package : com.xzl.entity
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 9:46
 * @Version: jdk 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_section")
@AllArgsConstructor
@NoArgsConstructor
public class SysSection extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -371133432982623791L;

    /**
     * 课节编号
     */
    private Double sectionNumber;
    /**
     * 课节名称
     */
    private String sectionName;
    /**
     * 开始时间
     */
    private LocalTime startTime;
    /**
     * 结束时间
     */
    private LocalTime endTime;

    public SysSection(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
