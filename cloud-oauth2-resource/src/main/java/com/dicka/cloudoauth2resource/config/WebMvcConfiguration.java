package com.dicka.cloudoauth2resource.config;

import com.dicka.cloudoauth2resource.model.CustomPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebMvcConfiguration implements WebMvcConfigurer{

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(currentHandlerResolver());
    }

    @Bean
    public HandlerMethodArgumentResolver currentHandlerResolver(){
        return new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter methodParameter) {
                return methodParameter
                        .getParameterType()
                        .equals(CustomPrincipal.class);
            }


            @Override
            public Object resolveArgument(MethodParameter parameter,
                                          ModelAndViewContainer container,
                                          NativeWebRequest request,
                                          WebDataBinderFactory factory) throws Exception{

                try{

                    return (CustomPrincipal) SecurityContextHolder
                            .getContext().getAuthentication().getPrincipal();

                }catch (Exception e){
                    return null;
                }
            }
        };
    }
}
