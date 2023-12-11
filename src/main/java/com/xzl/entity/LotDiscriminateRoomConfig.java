package com.xzl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * ClassName : LotDiscriminateRoomConfig
 * Package : com.xzl.entity
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 10:09
 * @Version: jdk 1.8
 */
@Data
@Table(name = "lot_discriminate_room_config")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LotDiscriminateRoomConfig implements Serializable {
    @Id
   private String id;
    /**
     * 房间类型,参照字典中的room类型
     */
   private String roomId;
    /**
     * 课节人数
     */
   private int personNumber;
    /**
     * 课节
     */
   private Double sectionNumber;
   private int weekDay;
   private int yearWeek;

}
