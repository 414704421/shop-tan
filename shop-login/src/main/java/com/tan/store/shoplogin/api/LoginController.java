package com.tan.store.shoplogin.api;


import com.tan.store.shoplogin.model.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @RequestMapping("login")
    public UserInfo login(UserInfo userInfo) {
       return userInfo;
    }

}
