package com.tan.store.shoprepo.dao;

import com.tan.store.shoprepo.dao.mapper.UserInfoMapper;
import com.tan.store.shoprepo.pojo.UserInfoPojo;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserInfoDao {

//    @Resource
//    UserInfoMapper userInfoMapper;

    public void insertUserInfo(UserInfoPojo userInfo) throws Exception {
        if(StringUtils.isBlank(userInfo.getUserName()) || StringUtils.isBlank(userInfo.getPassWord())){
            throw new Exception("用户名或密码不能为空!");
        }
        userInfo.setGmtCreated(new Date());
        userInfo.setGmtUpdated(new Date());
        try {
//            if (userInfoMapper.insert(userInfo) != 1){
//                throw new Exception("数据库插入失败！");
//            }
        } catch (Exception e) {
            log.error("数据库插入异常！{}",e.getMessage());
            throw new Exception("数据库插入失败！");
        }
    }
}
