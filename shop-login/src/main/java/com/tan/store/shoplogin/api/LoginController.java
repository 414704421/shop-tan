package com.tan.store.shoplogin.api;

import com.tan.store.shoplogin.aop.Login;
import com.tan.store.shoplogin.manager.LoginManager;
import com.tan.store.model.BaseResponse;
import com.tan.store.model.login.UserInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("login")
public class LoginController {
    @Resource
    LoginManager loginManager;

    @RequestMapping("login")
    public BaseResponse login(@RequestBody UserInfo userInfo, HttpServletResponse response) {
        try {
            if (loginManager.checkForLogin(userInfo)){
                String token = loginManager.keepLogin(userInfo);
                response.addCookie(new Cookie("token", token));
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return BaseResponse.failure(e.getMessage());
        }
        return BaseResponse.success();
    }

    @RequestMapping("logout")
    public BaseResponse logout(@RequestBody UserInfo userInfo) {
        try {
            if (!loginManager.logout(userInfo.getUserName())){
                return BaseResponse.failure("退出登录失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return BaseResponse.failure(e.getMessage());
        }
        return BaseResponse.success();
    }

    @Login
    @RequestMapping("test")
    public BaseResponse test(@RequestBody UserInfo userInfo) {
        return BaseResponse.success();
    }

}