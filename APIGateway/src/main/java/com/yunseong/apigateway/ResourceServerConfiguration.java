package com.yunseong.apigateway;

import com.yunseong.common.exception.InvalidValueException;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import java.util.Optional;

@Configuration
@AllArgsConstructor
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final OAuth2Configuration oAuth2Configuration;
    private final LoadBalancerClient loadBalancerClient;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .tokenServices(this.remoteTokenServices())
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                    .httpBasic().disable()
                    .csrf().disable()
                    .cors().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .anyRequest().anonymous();
    }

    @Bean
    public RemoteTokenServices remoteTokenServices() throws Exception {
        String[] token = this.oAuth2Configuration.getToken_uri().replace("http://", "").split("/");
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(Optional.of(this.loadBalancerClient.choose(token[0]).getUri().toString()).orElseThrow(() -> new InvalidValueException(token[0] + " 서비스가 중지된 상태입니다. 유레카 서버를 확인해주세요")));
        remoteTokenServices.setClientId(this.oAuth2Configuration.getClient_id());
        remoteTokenServices.setClientSecret(this.oAuth2Configuration.getClient_secret());
        return remoteTokenServices;
    }
}
