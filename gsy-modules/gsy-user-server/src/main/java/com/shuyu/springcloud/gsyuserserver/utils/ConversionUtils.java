package com.shuyu.springcloud.gsyuserserver.utils;

import com.shuyu.springcloud.db.user.entity.User;
import com.shuyu.springcloud.entity.SysRole;
import com.shuyu.springcloud.entity.UserVO;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConversionUtils {

    /**
     * 实体转换
     */
    public static UserVO conversionUserToDto(User user) {
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getId());
        userVO.setUsername(user.getAccount());
        userVO.setPassword(user.getPassword());
        userVO.setSalt(user.getSalt());

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = user.getCreatetime().atZone(zone).toInstant();
        userVO.setCreateTime(Date.from(instant));
        userVO.setUpdateTime(Date.from(instant));
        userVO.setDelFlag(user.getDeleted());
        userVO.setPhone(user.getPhone());
        userVO.setAvatar(user.getAvatar());
        userVO.setDeptId(user.getDeptid());
        userVO.setDeptName("");
        List<SysRole> list = new ArrayList<>();
        userVO.setRoleList(list);
        return userVO;
    }

}
