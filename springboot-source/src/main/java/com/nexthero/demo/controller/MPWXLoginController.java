package com.nexthero.demo.controller;

import com.nexthero.demo.model.UserInfo;
import com.nexthero.demo.pojo.MPWXUserBO;
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
 * @Description: 微信小程序登录
 */
@Api(value="微信小程序登录", tags={"微信小程序登录相关"})
@RestController
@RequestMapping(value = "api/user")
public class MPWXLoginController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value="微信小程序登录", notes="根据code进行用户的登录与注册", httpMethod = "POST")
    @PostMapping("/mpWXLogin/{code}")
    public NEXTJSONResult wxLogin(
            @ApiParam(name = "code", value = "通过wx.login获得的code", required = true)
            @PathVariable String code,
            @RequestBody MPWXUserBO wxUserBO) {
//

//        System.out.println("wxlogin - code: " + code);

        // 如果whichMP为空，则默认使用 默认的NEXT超英预告

//        Integer whichMP = wxUserBO.getWhichMP();
//
//        String appid = "wx1b89w6sffcbdd75g";
//        String secret = "f1we9c34w9f2ffwf18e9ee4ut25686dw";
//        if (whichMP == null) {
//            whichMP = 0;
//        } else if (whichMP == 0) {
//            appid = "wx1b89w6sffcbdd75g";
//            secret = "f1we9c34w9f2ffwf18e9ee4ut25686dw";
//        } else if (whichMP == 1) {
//            appid = "wx53ee2s5w795d4225";
//            secret = "6e86127c56aa86f0125i6be211e157b7";
//        } else if (whichMP == 2) {
//            appid = "wx4e9877da94088851";
//            secret = "ec12c916f6hwe29bb0375eff91fewf9d";
//        }

//		https://api.weixin.qq.com/sns/jscode2session?
//				appid=APPID&
//				secret=SECRET&
//				js_code=JSCODE&
//				grant_type=authorization_code

//        String url = "https://api.weixin.qq.com/sns/jscode2session";
//        Map<String, String> param = new HashMap<>();
//        param.put("appid", appid);
//        param.put("secret", secret);
//        param.put("js_code", code);
//        param.put("grant_type", "authorization_code");
//
//        String wxResult = HttpClientUtil.doGet(url, param);
//        System.out.println(wxResult);
//
//        WXSessionBO model = JsonUtils.jsonToPojo(wxResult, WXSessionBO.class);

        WXSessionBO model = new WXSessionBO();
        model.setOpenId("5404989893");

        // openid是微信端的用户唯一id，需要保存到数据库中
        String openId = model.getOpenId();
        boolean userIsExist = userInfoService.queryWXOpenIdIsExist(openId);
        UserInfo userResult;
        // 根据openid查询用户是否在数据库中存在，如果存在则直接登录，如果不存在则进行注册
        if (userIsExist) {
            // 登录
            userResult = userInfoService.queryUserForLoginByMPWX(openId);
        } else {
            // 注册
            userResult = userInfoService.saveUserMPWXOpenId(openId, wxUserBO);
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

