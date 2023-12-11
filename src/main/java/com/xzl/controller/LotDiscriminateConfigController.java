package com.xzl.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName : LotDiscriminateConfigController
 * Package : com.xzl.controller
 * Description :
 *
 * @Author : 欧显多
 * @Create : 2023/11/24 - 11:16
 * @Version: jdk 1.8
 */
@RestController
@RequestMapping(value = "/admin/disctiminate/config")
@Api(value = " 统计参数配置默认表（房间统计参数配置表没有数据，加载这个表默认数据。更具sys_room得房间类型关联）")
public class LotDiscriminateConfigController {
}
