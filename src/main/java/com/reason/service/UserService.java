package com.reason.service;

import com.reason.mapper.UserMapper;
import com.reason.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Reason.H.Duan
 * @Date: 7/10/2020
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public void userMigration(){
        List<UserInfo> userInfos =  userMapper.selectUser();
        logger.info("User list size:{}", userInfos.size());
        if(userInfos.size()>0){
            userMapper.batchUpdateInfo(userInfos);
        }
    }

}
