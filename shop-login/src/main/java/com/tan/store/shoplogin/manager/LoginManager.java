package com.tan.store.shoplogin.manager;


import com.tan.store.model.login.UserInfo;
import com.tan.store.shoplogin.aop.Login;
import com.tan.store.shoprepo.dao.UserInfoDao;
import com.tan.store.utils.RedisUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoginManager {
    @Resource
    UserInfoDao userInfoDao;
    @Resource
    RedisUtil redisUtil;

    private static final String REDIS_KEY = "login:user:";

    public boolean checkForLogin(UserInfo userInfo) throws Exception {
        if (userInfo == null || StringUtils.isBlank(userInfo.getUserName()) || StringUtils.isBlank(userInfo.getPassWord()) ) {
            throw new Exception("验证失败！用户名或密码不能为空！");
        }
        String token = redisUtil.get(getLoginKey(userInfo.getUserName()));
        if (StringUtils.isNotBlank(token)) {
            throw new Exception("用户已登录，请勿重复登录！");
        }

        String psw = userInfoDao.getPassWord(userInfo.getUserName());
        if (!userInfo.getPassWord().equals(psw)) {
            throw new Exception("验证失败！用户名或密码不正确!");
        }
        return true;
    }

    public String keepLogin(UserInfo userInfo) throws Exception {
        String token = UUID.randomUUID().toString();
        userInfo.setToken(token);
        if (!redisUtil.setWithExpire(getLoginKey(userInfo.getUserName()),token,3600L)){
            throw new Exception("登录状态保存失败");
        }
        return token;
    }

    public boolean logout(String userName) throws Exception {
        String token = redisUtil.get(getLoginKey(userName));
        if (StringUtils.isBlank(token)) {
            throw new Exception("退出登录失败，用户未登录!");
        }
        if (!redisUtil.delKey(getLoginKey(userName))) {
            throw new Exception("退出登录失败");
        }
        return true;
    }

    public static String getLoginKey(String s){
        if (StringUtils.isBlank(s)) return "";
        return REDIS_KEY + s;
    }
}
