package com.tan.store.shoplogin.api;

import com.tan.store.model.BaseResponse;
import com.tan.store.model.login.UserInfo;
import com.tan.store.shoprepo.dao.UserInfoDao;
import com.tan.store.shoprepo.pojo.UserInfoPojo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Resource
    UserInfoDao userInfoDao;

    @RequestMapping("register")
    public BaseResponse register(@RequestBody UserInfo userInfo) throws Exception {
        BaseResponse response = new BaseResponse();
        UserInfoPojo userInfoPojo = new UserInfoPojo();
        userInfoPojo.setUserName(userInfo.getUserName());
        userInfoPojo.setPassWord(userInfo.getPassWord());
        userInfoPojo.setNick(userInfo.getUserNick());
        try {
            this.userInfoDao.insertUserInfo(userInfoPojo);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMsg(e.getMessage());
            response.setCode(500);
        }
        return response;
    }
}
