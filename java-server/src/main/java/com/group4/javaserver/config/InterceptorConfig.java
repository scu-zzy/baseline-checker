package com.group4.javaserver.config;

import com.group4.javaserver.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry){

        //添加对用户是否登录的拦截器，并添加过滤项、排除项
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/fonts/**","/images/**","/js/**", "/lib/layui/**")//排除资源文件
                .excludePathPatterns("/login/page")//排除登录页面
                .excludePathPatterns("/login/do");//排除登录操作
    }

}
