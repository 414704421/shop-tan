package com.tan.store.shoprepo.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tan.store.shoprepo.dao.mapper.UserInfoMapper;
import com.tan.store.shoprepo.pojo.UserInfoPojo;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserInfoDao {

    @Resource
    UserInfoMapper userInfoMapper;

    public void insertUserInfo(UserInfoPojo userInfo) throws Exception {
        if(StringUtils.isBlank(userInfo.getUserName()) || StringUtils.isBlank(userInfo.getPassWord())){
            throw new Exception("用户名或密码不能为空!");
        }
        userInfo.setGmtCreated(new Date());
        userInfo.setGmtUpdate(new Date());
        try {
            if (userInfoMapper.insert(userInfo) != 1){
                throw new Exception("数据库插入失败！");
            }
        } catch (Exception e) {
            log.error("数据库插入异常！{}",e.getMessage());
            throw new Exception("数据库插入失败！");
        }
    }

    public String getPassWord(String userName) throws Exception {
        UserInfoPojo userInfoPojo = new UserInfoPojo();
        userInfoPojo.setUserName(userName);
        userInfoPojo = userInfoMapper.selectByParam(userInfoPojo);
        if(userInfoPojo == null){
            throw new Exception("未找到用户信息");
        }
        return userInfoPojo.getPassWord();
    }

    public UserInfoPojo queryUserInfoByUserName(String userName) throws Exception {
        if (StringUtils.isBlank(userName)){
            throw new Exception("用户名不能为空");
        }
        UserInfoPojo userInfoPojo = new UserInfoPojo();
        userInfoPojo.setUserName(userName);
        Wrapper<UserInfoPojo> wrapper = new QueryWrapper<>(userInfoPojo);
        UserInfoPojo res = null;
        try {
            res = userInfoMapper.selectOne(wrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
