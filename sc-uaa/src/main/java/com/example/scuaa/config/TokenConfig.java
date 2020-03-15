package com.example.scuaa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@RefreshScope
public class TokenConfig {

    // 采用对称加密，授权服务器和资源服务器秘钥一致
    @Value("${signing.key}")
    private String SIGNING_KEY;
//    private final String SIGNING_KEY = "uaa123";

    @Bean
    public TokenStore tokenStore() {
        // JWT令牌存储方案
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(SIGNING_KEY); // 对称秘钥，资源服务器使用该秘钥验证jwt
        return accessTokenConverter;
    }

    /*@Bean
    public TokenStore tokenStore() {
        // 使用内存存储token（普通令牌）
        return new InMemoryTokenStore();
    }*/
}
