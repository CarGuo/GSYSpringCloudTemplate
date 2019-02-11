package com.shuyu.springcloud.auth.utils;

import com.alibaba.fastjson.JSON;
import com.shuyu.springcloud.entity.UserVO;

import java.util.Map;


public class OAuthUtils {

    /**
     * 提取map中的uservo
     */
    public static UserVO getUserVoByMap(Object obj) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof Map)) {
            return null;
        }
        Map<String, Object> map = (Map<String, Object>)obj;
        if (!map.containsKey("data")) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(map.get("data")), UserVO.class);
    }

}
