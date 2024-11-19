package com.tan.store.shoplogin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CommonResponse {
    private Integer code;
    private String msg;
    private boolean success;

    public CommonResponse() {
        this.success = true;
        this.msg = "success";
    }

    public static CommonResponse failure(String msg) {
        return new CommonResponse(500,msg,false);
    }

    public static CommonResponse success() {
        return new CommonResponse(200,"success",true);
    }
}
