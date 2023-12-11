package com.xzl.dao;

import com.xzl.common.base.BaseDAO;
import com.xzl.entity.SysSection;
import org.springframework.stereotype.Repository;

/**
 * ClassName : SysSectionDAO
 * Package : com.xzl.dao
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 10:52
 * @Version: jdk 1.8
 */
@Repository
public interface SysSectionDAO extends BaseDAO<SysSection> {
    SysSection findBySectionNumber(Double sectionNumber);
}
