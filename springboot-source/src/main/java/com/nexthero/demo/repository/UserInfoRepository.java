package com.nexthero.demo.repository;

import com.nexthero.demo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserInfoRepository
 *
 * @version 1.0
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
    UserInfo findByMpWxOpenId(String openId);
    UserInfo findByAppWxOpenId(String openId);
    UserInfo findByAppQqOpenId(String openId);
    UserInfo findByAppWeiboUId(String openId);
}
