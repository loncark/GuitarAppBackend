package com.loncark.guitarapp.repository;

import com.loncark.guitarapp.model.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository {

    Optional<UserInfo> findByName(String name);

    Optional<UserInfo> save(UserInfo userInfo);
}
