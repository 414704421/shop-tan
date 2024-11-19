package com.tan.store.shoplogin.api;

import com.tan.store.shoplogin.manager.LoginManager;
import com.tan.store.shoplogin.model.CommonResponse;
import com.tan.store.shoplogin.model.entity.UserInfo;
import jakarta.annotation.Resource;
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
    public CommonResponse login(@RequestBody UserInfo userInfo) {
        try {
            if (loginManager.checkForLogin(userInfo)){
                loginManager.keepLogin(userInfo);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return CommonResponse.failure(e.getMessage());
        }
        return CommonResponse.success();
    }

}