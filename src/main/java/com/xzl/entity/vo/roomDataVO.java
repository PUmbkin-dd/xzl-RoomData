package com.xzl.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.NamedEntityGraph;

/**
 * ClassName : roomDataVO
 * Package : com.xzl.entity.vo
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 15:31
 * @Version: jdk 1.8
 */
@Data
@AllArgsConstructor
@NamedEntityGraph
public class roomDataVO {
    /**
     * 房间号
     */
    private String number;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否共享是、否
     */
    private String shareStatus;

}
