package com.yunseong.member.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "oauth2")
public class OAuth2Configuration {

    private String client_id;
    private String client_secret;
    private String jwt_secret;
    private int access_token_expire;
    private int refresh_token_expire;
    private String token_uri;
}
