package com.dicka.cloudoauth2jwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter{

    @Value("${check-user-scopes}")
    private boolean checkUsersScopes;

    /** pengambilan clients resource dari database **/
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtConverter = new CustomTokenEnhancer();
        jwtConverter.setKeyPair(new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"),
                "password".toCharArray()).getKeyPair("jwt"));
        return jwtConverter;
    }

    @Bean
    public OAuth2RequestFactory requestFactory(){
        CustomOauth2RequestFactory customOauth2RequestFactory = new CustomOauth2RequestFactory(clientDetailsService);
        customOauth2RequestFactory.setCheckUserScopes(true);
        return customOauth2RequestFactory;
    }

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.jdbc(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter(){
        return new TokenEndpointAuthenticationFilter(authenticationManager, requestFactory());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauth2server) throws Exception{
        oauth2server.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(jwtAccessTokenConverter())
                    .authenticationManager(authenticationManager)
                        .userDetailsService(userDetailsService);

        /** jika cehck user scopesnya true **/
        if (checkUsersScopes)
            endpoints.requestFactory(requestFactory());
    }


}
