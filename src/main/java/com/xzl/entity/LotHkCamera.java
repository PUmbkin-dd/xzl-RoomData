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
 * ClassName : lot_hk_camera
 * Package : com.xzl.entity
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 9:57
 * @Version: jdk 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "lot_hk_camera ")
@AllArgsConstructor
@NoArgsConstructor
public class LotHkCamera extends BaseEntity implements Serializable {
    /**
     * 房间Id
     */
   private String roomId;
    /**
     * 房间名称
     */
   private String roomName;
    /**
     * 摄像机osb名称
     */
   private String osb;
    /**
     * 摄像机ip
     */
   private String cameraIp;
    /**
     * 摄像机端口
     */
   private String cameraPort;
    /**
     * 摄像机用户名
     */
   private String cameraUser;
    /**
     * 摄像机密码
     */
   private String cameraPass;
    /**
     * rtspUrl
     */
   private String cameraRtspUrl;
    /**
     * 指定录像机id
     */
   private String nvrId;
    /**
     * 摄像机类型：0-普通摄像机， 1-人流
     */
   private String cameraType;
}
