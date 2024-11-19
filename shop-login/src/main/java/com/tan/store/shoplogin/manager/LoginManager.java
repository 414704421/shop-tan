package com.tan.store.shoplogin.manager;


import com.tan.store.shoplogin.model.entity.UserInfo;
import com.tan.store.shoprepo.dao.UserInfoDao;
import com.tan.store.utils.RedisUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoginManager {
    @Resource
    UserInfoDao userInfoDao;
    @Resource
    RedisUtil redisUtil;

    public boolean checkForLogin(UserInfo userInfo) throws Exception {
        if (userInfo == null || StringUtils.isBlank(userInfo.getUserName()) || StringUtils.isBlank(userInfo.getPassWord()) ) {
            throw new Exception("验证失败！用户名或密码不能为空！");
        }
        String psw = userInfoDao.getPassWord(userInfo.getUserName());
        if (!userInfo.getPassWord().equals(psw)) {
            throw new Exception("验证失败！用户名或密码不正确!");
        }
        return true;
    }

    public void keepLogin(UserInfo userInfo) throws Exception {
        String token = UUID.randomUUID().toString();
        userInfo.setToken(token);
        if (!redisUtil.putExpire(userInfo.getUserName(),token,3600L)){
            throw new Exception("登录状态保存失败");
        }
    }
}
