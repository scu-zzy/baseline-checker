package com.group4.javaserver.config;

import com.group4.javaserver.shiro.CustomRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description TODO
 * @Author fqCoder
 * @Date 2020/2/29 3:08
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {
    @Bean
    CustomRealm myRealm() {
        return new CustomRealm();
    }
    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login/page");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/notfound/page");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/login/do", "anon");
        map.put("/login/page","anon");
        map.put("/css/**","anon");
        map.put("/fonts/**","anon");
        map.put("/images/**","anon");
        map.put("/js/**","anon");
        map.put("/lib/layui/**","anon");

        map.put("/admin/**", "roles[1]");

        map.put("/device/**", "roles[2]");
        map.put("/**", "authc");

        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
}