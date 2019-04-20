package com.example.bbzn;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@ComponentScan
@Configuration
public class TextSession extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(interceptor()).excludePathPatterns("/api/user/*","/api/login/*");*/
        registry.addInterceptor(interceptor()).excludePathPatterns("/api/user/*","/api/login/","/api/login/loginVerification");
    }

    @Bean
    public Interceptor interceptor(){
        Interceptor interceptor = new Interceptor();
        return interceptor;
    }

}
