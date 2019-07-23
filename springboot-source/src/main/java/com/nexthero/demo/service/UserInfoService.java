package com.nexthero.demo.service;

import com.nexthero.demo.model.UserInfo;
import com.nexthero.demo.pojo.MPWXUserBO;
import com.nexthero.demo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.UUID;

/**
 * UserInfoService
 *
 * @version 1.0
 */
@Service
public class UserInfoService {
    private UserInfoRepository userInfoRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    // 根据用户名查找用户
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }


    // 注册用户
    @Transactional
    public UserInfo register(UserInfo userInfo) {
        String password = userInfo.getPassword();
        String encodedPass = DigestUtils.md5DigestAsHex(password.getBytes());
        String userUniqueToken = UUID.randomUUID().toString();
        userInfo.setPassword(encodedPass);
        userInfo.setNickname(userInfo.getUsername());
        userInfo.setUserUniqueToken(userUniqueToken);

        UserInfo ret = userInfoRepository.save(userInfo);//因为缓存原因，数据库数据没更新到实体类中，用entityManager.refresh(Entity)方法更新下就好，否则会拿不到default的faceImage字段数据
        entityManager.refresh(ret);  //这会发送一条select请求
        return ret;
    }


    // 用户登录验证
    public Boolean validation(UserInfo savedUser, UserInfo loginUser) {
        return savedUser.getPassword().equals(DigestUtils.md5DigestAsHex(loginUser.getPassword().getBytes()));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryWXOpenIdIsExist(String openId) {
        UserInfo userInfo = userInfoRepository.findByMpWxOpenId(openId);
        return userInfo != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserInfo saveUserMPWXOpenId(String openId, MPWXUserBO mpwxUserBO) {
        UserInfo user = new UserInfo();
        user.setMpWxOpenId(openId);
        user.setNickname(mpwxUserBO.getNickName());

        user.setFaceImage(mpwxUserBO.getAvatarUrl());
        user.setBirthday(new Date(1900, 1, 1));
        user.setIsCertified(0);
        user.setRegistTime(new Date(System.currentTimeMillis()));
        String userUniqueToken = UUID.randomUUID().toString();
        user.setUserUniqueToken(userUniqueToken);
        user.setUsername(mpwxUserBO.getNickName());

        userInfoRepository.save(user);
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public UserInfo queryUserForLoginByMPWX(String openId) {
        return userInfoRepository.findByMpWxOpenId(openId);
    }


}
