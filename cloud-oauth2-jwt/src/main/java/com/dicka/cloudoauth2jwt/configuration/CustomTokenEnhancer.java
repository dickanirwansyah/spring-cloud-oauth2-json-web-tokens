package com.dicka.cloudoauth2jwt.configuration;

import com.dicka.cloudoauth2jwt.entity.Users;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication){
        Users users = (Users) authentication.getPrincipal();
        Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
        info.put("email", users.getEmail());
        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);

        return super.enhance(customAccessToken, authentication);
    }
}
