package com.xzl.dao;

import com.xzl.common.base.BaseDAO;
import com.xzl.entity.LotDiscriminateRoomConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName : LotDiscriminataRoomDAO
 * Package : com.xzl.dao
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 10:56
 * @Version: jdk 1.8
 */
@Repository
public interface LotDiscriminateRoomConfigDAO extends BaseDAO<LotDiscriminateRoomConfig> {
    List<LotDiscriminateRoomConfig> findAllByRoomId(String id);
}
