package com.nexthero.demo.controller;

import com.nexthero.demo.model.UserInfo;
import com.nexthero.demo.model.RespBean;
import com.nexthero.demo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * UserInfoController
 *
 * @version 1.0
 */
@RestController
@RequestMapping(value = "api/user")
@Api(value = "用户信息的api接口")
public class UserInfoController {
    private UserInfoService userInfoService;

    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("registOrLogin")
    @ApiOperation(value = "登录或注册用户")
    public Map<String, Object> registOrLogin(@RequestBody UserInfo userInfo) {
        Map<String, Object> map = new HashMap<>();
        UserInfo res = null;
        RespBean respBean = null;

        if (userInfo != null &&
                (!"".equals(userInfo.getUsername().trim())) &&
                (!"".equals(userInfo.getPassword().trim()))) {
            // 判断用户是否已注册
            UserInfo resUser = userInfoService.findByUsername(userInfo.getUsername());

            if (resUser == null) {          //注册用户
                res = userInfoService.register(userInfo);
                res.setPassword("");
                respBean = new RespBean("201", "已注册新用户");
            } else {                // 用户登录
                Boolean valid = userInfoService.validation(resUser, userInfo);
                if (valid) {
                    res = resUser;
                    res.setPassword("");
                    respBean = new RespBean("200", "登录成功");
                } else {
                    res = null;
                    respBean = new RespBean("401", "密码不正确");
                }
            }
        } else {
            respBean = new RespBean("500", "用户名密码不能为空");
        }

        map.put("status", respBean.getStatus());
        map.put("msg", respBean.getMsg());
        map.put("data", res);
        return map;
    }


    @PostMapping("logout")
    @ApiOperation(value = "退出登录")
    public Map<String, Object> logout(@RequestParam(value = "userId") Long userId) {
        Map<String, Object> map = new HashMap<>();
        UserInfo res = null;
        RespBean respBean = null;

        respBean = new RespBean("200", "退出登录成功");
        map.put("status", respBean.getStatus());
        map.put("msg", respBean.getMsg());
        map.put("data", res);
        return map;
    }

}
