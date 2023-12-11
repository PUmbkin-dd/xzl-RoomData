package com.xzl.entity;

import com.xzl.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * ClassName : SysRoom
 * Package : com.xzl.entity
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/23 - 17:23
 * @Version: jdk 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_room")
@NoArgsConstructor
@AllArgsConstructor
public class SysRoom extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -371132791L;
    /**
     *校区号id
     */
   private String xqhId;
    /**
     * 楼栋id
     */
   private String buildingId;
    /**
     * 楼层id
     */
   private String floorId;
    /**
     * 名称
     */
   private String name;
    /**
     * 房间号
     */
   private String number;
    /**
     * 楼栋编号（用于模型展示）
     */
   private String modelNumber;
    /**
     * 模型编号（用于模型展示）
     */
   private String mxId;
    /**
     * 负责人id；对应sys_user表user_info_id列
     */
   private String inChargePersonId;
    /**
     * 第二课堂审批员
     */
   private String twoApproverId;
    /**
     * 实验室面积
     */
   private Double areas;
    /**
     * 负责人
     */
   private String inChargePerson;
    /**
     * 房间类型，参数字典中的room类型
     */
   private Integer type;
    /**
     * 是否开放预约:0-否，1-是
     */
   private Integer openStatus;
    /**
     * ip地址
     */
   private String ipAddr;
    /**
     * 备注
     */
   private String remark;
    /**
     * 第二课堂审批员
     */
   private String twoApproverPerson;
    /**
     * 是否禁用，0：否，1：是
     */
   private Integer status;
    /**
     * 是否共享是、否
     */
   private String shareStatus;


}
