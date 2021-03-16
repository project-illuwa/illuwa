package com.yunseong.apigateway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "oauth2")
public class OAuth2Configuration {

    private String client_id;
    private String client_secret;
    private String token_uri;
}
