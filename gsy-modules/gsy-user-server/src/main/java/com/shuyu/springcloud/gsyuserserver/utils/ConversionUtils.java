package com.shuyu.springcloud.gsyuserserver.utils;

import com.shuyu.springcloud.db.user.entity.User;
import com.shuyu.springcloud.entity.UserVO;

public class ConversionUtils {

    public static UserVO converUserToDto(User user) {
        UserVO userVO = new UserVO();
        userVO.setDeptId(user.getDeptid());
        userVO.setAvatar(user.getAvatar());
        userVO.setPhone(user.getPhone());
        userVO.setSalt(user.getSalt());
        userVO.setPassword(user.getPassword());
        userVO.setUsername(user.getAccount());
        userVO.setUserId(user.getId());
        return  userVO;
    }

}
