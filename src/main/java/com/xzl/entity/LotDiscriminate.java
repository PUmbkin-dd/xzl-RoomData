package com.xzl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ClassName : LotDiscriminate
 * Package : com.xzl.entity
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 9:54
 * @Version: jdk 1.8
 */
@Data
@Entity
@Table(name = "lot_discriminate")
@AllArgsConstructor
@NoArgsConstructor
public class LotDiscriminate implements Serializable {
    @Id
    private String id ;
    private String hkCameraId;
    private String roomId;
    private LocalDateTime recordTime;
    private String recordPath;
    private Integer personNum;

}
