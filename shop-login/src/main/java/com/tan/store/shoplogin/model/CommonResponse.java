package com.tan.store.shoplogin.model;

import lombok.Data;

@Data
public class CommonResponse {
    private Integer code;
    private String msg;
    private boolean success;

    public CommonResponse() {
        this.success = true;
        this.msg = "注册成功！";
    }
}
