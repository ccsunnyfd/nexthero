package com.nexthero.demo.repository;

import com.nexthero.demo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserInfoRepository
 *
 * @version 1.0
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByUsername(String username);
}
