package com.shuyu.springcloud.auth.utils;

import com.shuyu.springcloud.entity.SysRole;
import com.shuyu.springcloud.entity.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUtils {

    public static UserVO generateUserVo() {
        UserVO userVO = new UserVO();
        userVO.setUserId(54584558);
        userVO.setUsername("carguo");
        userVO.setPassword("123456");
        userVO.setSalt("uf555");
        userVO.setCreateTime(new Date());
        userVO.setUpdateTime(new Date());
        userVO.setDelFlag(0);
        userVO.setPhone("13421854952");
        userVO.setAvatar("xxxxxxxx");
        userVO.setDeptId(1244111);
        userVO.setDeptName("hh");
        List<SysRole> list = new ArrayList<>();
        userVO.setRoleList(list);
        return userVO;
    }

}
