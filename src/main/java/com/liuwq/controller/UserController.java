package com.liuwq.controller;

import com.liuwq.po.RspInfoBO;
import com.liuwq.po.User;
import com.liuwq.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/23 0023 上午 11:40
 * @version: V1.0
 */
@Controller
@Api(value = "回访活动管理")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/queryUserPage")
    @ApiOperation(value = "分页查询用户", httpMethod = "POST", response = RspInfoBO.class, notes = "分页查询用户")
    @ResponseBody
    public RspInfoBO queryMaPushModelRelPage(@RequestBody User bo) {
        RspInfoBO rspInfoBO = userService.queryListByCondition(bo);
        return rspInfoBO;
    }

    @GetMapping("/selectUserById")
    @ApiOperation(value = "根据id查询用户", httpMethod = "GET", response = User.class, notes = "根据id查询用户")
    @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "Integer", paramType = "query")
    @ResponseBody
    public User selectUserById(@RequestParam Integer id) {
        log.info(">>>UserController...selectUserByid()...");
        User user = userService.selectUser(id);
        return user;
    }

    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户", httpMethod = "POST", response = RspInfoBO.class, notes = "修改用户")
    @ResponseBody
    public RspInfoBO updateUser(@RequestBody User bo) {
        log.info("修改用户");
        RspInfoBO rspInfoBO = userService.updateUser(bo);
        return rspInfoBO;
    }

    @PostMapping("/insertUser")
    @ApiOperation(value = "新增用户", httpMethod = "POST", response = RspInfoBO.class, notes = "新增用户")
    @ResponseBody
    public RspInfoBO insertUser(@RequestBody User bo) {
        log.info("新增用户");
        RspInfoBO rspInfoBO = userService.insertUser(bo);
        return rspInfoBO;
    }

    @GetMapping("/deleteUser")
    @ApiOperation(value = "删除用户", httpMethod = "GET", response = RspInfoBO.class, notes = "删除用户")
    @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "Integer", paramType = "query")
    @ResponseBody
    public RspInfoBO deleteMaRevisitAction(@RequestParam Integer id) {
        log.info("删除用户，id{}", id);
        RspInfoBO rspInfoBO = userService.deleteUserById(id);
        return rspInfoBO;
    }

}
