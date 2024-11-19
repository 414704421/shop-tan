package com.tan.store.model.login;

import lombok.Data;

@Data
public class UserInfo {
    private String userName;
    private String passWord;
    private String userNick;
    private String token;
}
