package com.xzl.service;

import com.xzl.common.model.NgPager;
import com.xzl.entity.Pager.Pager;
import com.xzl.entity.vo.DataVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName : SysRoomService
 * Package : com.xzl.service
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 10:47
 * @Version: jdk 1.8
 */
public interface SysRoomService {

    List<DataVO> list(Pager pager);


}
