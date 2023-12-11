package com.xzl.controller;

import com.xzl.common.model.NgData;
import com.xzl.common.model.NgPager;
import com.xzl.common.response.ObjectResponse;
import com.xzl.entity.Pager.Pager;
import com.xzl.entity.vo.DataVO;
import com.xzl.service.SysRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName : SysRoomController
 * Package : com.xzl.controller
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 11:06
 * @Version: jdk 1.8
 */
@RestController
@RequestMapping(value = "/admin/room")
@Api(value = "房间表")
public class SysRoomController {

    @Autowired
    private SysRoomService sysRoomService;



    @GetMapping(value = "list")
    @ApiOperation(value = "获取所有统计数据")
    public Object list(Pager pager){
        return new ObjectResponse<>(sysRoomService.list(pager));
    }

}
