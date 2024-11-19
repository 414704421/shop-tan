package com.tan.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse {
    private Integer code;
    private String msg;
    private boolean success;

    public BaseResponse() {
        this.success = true;
        this.msg = "success";
    }

    public static BaseResponse failure(String msg) {
        return new BaseResponse(500,msg,false);
    }

    public static BaseResponse success() {
        return new BaseResponse(200,"success",true);
    }
}
