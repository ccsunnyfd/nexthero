package com.nexthero.demo.controller;

import com.nexthero.demo.model.UserInfo;
import com.nexthero.demo.pojo.AppUnionUserBO;
import com.nexthero.demo.pojo.WXSessionBO;
import com.nexthero.demo.service.UserInfoService;
import com.nexthero.demo.utils.NEXTJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @Description: APP登录
 */
@Api(value="App登录", tags={"App登录相关"})
@RestController
@RequestMapping(value = "api/user")
public class AppUnionLoginController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value="App登录", notes="App使用微信、QQ或新浪微博实现用户的登录与注册", httpMethod = "POST")
    @PostMapping("/appUnionLogin/{logintype}")
    public NEXTJSONResult appUnionLogin(
            @ApiParam(name = "logintype", value = "使用微信、QQ或新浪微博其中一种类型", required = true)
            @PathVariable String logintype,
            @RequestBody AppUnionUserBO appUnionUserBO) {


//        WXSessionBO model = JsonUtils.jsonToPojo(wxResult, WXSessionBO.class);

        WXSessionBO model = new WXSessionBO();
        model.setOpenId(appUnionUserBO.getOpenIdOrUid());

        // openid是微信端的用户唯一id，需要保存到数据库中
        String openId = model.getOpenId();
        boolean userIsExist = userInfoService.queryAppUnionOpenIdIsExist(openId, logintype);
        UserInfo userResult;
        // 根据openid查询用户是否在数据库中存在，如果存在则直接登录，如果不存在则进行注册
        if (userIsExist) {
            // 登录
            userResult = userInfoService.queryUserForAppLogin(openId, logintype);
        } else {
            // 注册
            userResult = userInfoService.saveUserAppUnion(logintype, appUnionUserBO);
        }

//        Long userId = userResult.getId();

//        String uniqueToken = UUID.randomUUID().toString().trim();
//        redisOperator.set(REDIS_USER_TOKEN + ":" + userId, uniqueToken);
//
//        UsersVO userVO = new UsersVO();
//        BeanUtils.copyProperties(userResult, userVO);
//        userVO.setUserUniqueToken(uniqueToken);

//        return NEXTJSONResult.ok(userVO);
        return NEXTJSONResult.ok(userResult);
    }

}

