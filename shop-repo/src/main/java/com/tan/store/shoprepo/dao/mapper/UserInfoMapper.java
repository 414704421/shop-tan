package com.tan.store.shoprepo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tan.store.shoprepo.pojo.UserInfoPojo;

public interface UserInfoMapper extends BaseMapper<UserInfoPojo> {

    UserInfoPojo selectByParam(UserInfoPojo userInfoPojo);
}
