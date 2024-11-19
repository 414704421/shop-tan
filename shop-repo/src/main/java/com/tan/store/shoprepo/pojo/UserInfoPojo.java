package com.tan.store.shoprepo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoPojo {
    private Integer id;
    private String userName;
    private String passWord;
    private String nick;
    private String headPic;
    private Date gmtCreated;
    private Date gmtUpdate;
}
