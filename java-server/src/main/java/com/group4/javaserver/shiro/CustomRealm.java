package com.group4.javaserver.shiro;

import com.group4.javaserver.dao.*;
import com.group4.javaserver.exception.LoginException;
import com.group4.javaserver.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MapperScan("com.gxa.javaserver.dao")
public class CustomRealm extends AuthorizingRealm {


    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RuleMapper rolePermissionMapper;

    /**
     * 对用户进行身份认证
     * @param authenticationToken
     * @return SimpleAuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        Admin admin=adminMapper.findByName(token.getUsername());

        if (admin == null) {
            System.out.println("用户名不正确");
            throw new LoginException(1001,"用户名不正确");

        }else if (!admin.getAdminPwd().equals(String.valueOf(token.getPassword())) ){
            System.out.println("密码不正确");
            throw new LoginException(1002,"密码不正确");
        }

        return new SimpleAuthenticationInfo(token.getPrincipal(), admin.getAdminPwd(), getName());
    }

    /**
     * 获取用户角色和权限
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println("进入角色授权");
        String userName = (String) SecurityUtils.getSubject().getPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<UserRole> userRoles = userRoleMapper.getItemsByName(userName);
        Set<String> roles = new HashSet<>();

        for (UserRole role : userRoles){
            roles.add(role.getRoleCode().toString());
        }
        info.setRoles(roles);
        return info;
    }


}
