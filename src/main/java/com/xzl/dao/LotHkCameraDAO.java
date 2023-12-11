package com.xzl.dao;

import com.xzl.common.base.BaseDAO;
import com.xzl.entity.LotHkCamera;
import jdk.internal.org.objectweb.asm.tree.analysis.SourceValue;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName : LotHkCameraDAO
 * Package : com.xzl.dao
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 10:53
 * @Version: jdk 1.8
 */
@Repository
public interface LotHkCameraDAO extends BaseDAO<LotHkCamera> {
    List<LotHkCamera> findAllByRoomId(String id);
}
