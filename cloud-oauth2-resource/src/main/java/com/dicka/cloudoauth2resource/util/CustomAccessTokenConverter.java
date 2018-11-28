package com.dicka.cloudoauth2resource.util;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessTokenConverter implements AccessTokenConverter,
        JwtAccessTokenConverterConfigurer{

    private boolean includeGrantType;

    private UserAuthenticationConverter userAuthenticationConverter = new CustomUserAuthenticationConverter();

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        return null;
    }

    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(value);
        Map<String, Object> info = new HashMap<String, Object>(map);

        info.remove(EXP);
        info.remove(AUD);
        info.remove(CLIENT_ID                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              )
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        return null;
    }

    @Override
    public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
        jwtAccessTokenConverter.setAccessTokenConverter(this);
    }
}
