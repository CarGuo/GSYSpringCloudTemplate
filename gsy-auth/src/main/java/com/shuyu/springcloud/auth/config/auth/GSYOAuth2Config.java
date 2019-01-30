package com.shuyu.springcloud.auth.config.auth;

import com.shuyu.springcloud.entity.global.CommonConstant;
import com.shuyu.springcloud.entity.global.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权服务配置
 */
@Configuration
@EnableAuthorizationServer
public class GSYOAuth2Config extends AuthorizationServerConfigurerAdapter {


    /**
     * 从 Security 注入 AuthenticationManager
     */
    private final AuthenticationManager authenticationManagerBean;

    /**
     * 自定义用户密码登录服务
     */
    @Autowired
    @Qualifier("userDetailService")
    private UserDetailsService userDetailsService;

    /**
     * 注入 redis 缓存 token
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 注入 dataSource 用于自定义数据库oauth表
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 构造配置需要注入 AuthenticationManager
     */
    @Autowired
    public GSYOAuth2Config(AuthenticationManager authenticationManagerBean) {
        this.authenticationManagerBean = authenticationManagerBean;
    }

    /**
     * 配置OAuth2的客户端相关信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //自定义了授权表
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
        clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 括配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory等等
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        //token增强配置，增加token返回信息
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));

        endpoints.tokenStore(redisTokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManagerBean)
                .reuseRefreshTokens(false)
                .userDetailsService(userDetailsService);
    }

    /**
     * 安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter 核心过滤器
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }

    /**
     * 不用密码加密服务
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new NoPasswordEncoder();
    }

    /**
     * 自定义 Token 信息
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        GSYJwtAccessTokenConverter jwtAccessTokenConverter = new GSYJwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(CommonConstant.SIGN_KEY);
        return jwtAccessTokenConverter;
    }

    /**
     * tokenstore 定制化处理，存入redis
     *
     * 如果使用的 redis-cluster 模式请使用 GSYRedisTokenStore
     * GSYRedisTokenStore tokenStore = new GSYRedisTokenStore();
     * tokenStore.setRedisTemplate(redisTemplate);
     */
    @Bean
    public TokenStore redisTokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(SecurityConstants.GSY_PREFIX);
        return tokenStore;
    }

    /**
     * jwt 生成token 定制化处理
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(2);
            additionalInfo.put("license", SecurityConstants.GSY_LICENSE);
            UserDetailsImpl user = (UserDetailsImpl) authentication.getUserAuthentication().getPrincipal();
            if (user != null) {
                additionalInfo.put("userId", user.getUserId());
            }
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
}
