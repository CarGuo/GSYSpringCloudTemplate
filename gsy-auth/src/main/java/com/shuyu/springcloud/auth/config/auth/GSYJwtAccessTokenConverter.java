package com.shuyu.springcloud.auth.config.auth;

import com.shuyu.springcloud.entity.global.SecurityConstants;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * 自定义 Token
 */
public class GSYJwtAccessTokenConverter extends JwtAccessTokenConverter {

    /**
     * 给token信息增加 license
     */
    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Map<String, Object> representation = (Map<String, Object>) super.convertAccessToken(token, authentication);
        representation.put("license", SecurityConstants.GSY_LICENSE);
        return representation;
    }

    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
        return super.extractAccessToken(value, map);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        return super.extractAuthentication(map);
    }
}
