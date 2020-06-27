package com.group4.javaserver.controller;

import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.Admin;
import com.group4.javaserver.pojo.PageVo;
import com.group4.javaserver.service.AccessService;
import com.group4.javaserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccessController {
    @Autowired
    private AccessService accessService;
    @Autowired
    private RoleService roleService;
    /**
     *
     * 权限分类页面
     */
    @PostMapping("access/cate/list")
    @ResponseBody
    public ResultDO permissionList(){
        return accessService.queryAllList();
    }
    /**
     *
     * 角色信息页面
     */
    @PostMapping("/access/roles")
    @ResponseBody
    public ResultDO queryRoles(){
        return this.roleService.queryAll();
    }

    /**
     *
     * 添加新的权限
     */
    @PostMapping("access/addnew")
    @ResponseBody
    public ResultDO addNewPermission(String permission){
        return accessService.addNewItem(permission);
    }
    /**
     *
     * 更改角色页面
     */
    @GetMapping("/access/edit")
    public String networkInfoPage(){
        return "access/edit";
    }
    /**
     *
     * 获取指定用户的角色
     */
    @PostMapping("/access/user-role/name")
    @ResponseBody
    public ResultDO queryUserRoleByName(Admin admin){
        return this.accessService.queryUserRoleByName(admin);
    }

    /**
     * 查询所有类型的角色
     *
     */
    @PostMapping("/access/role/list")
    @ResponseBody
    public ResultDO queryAll(){
        return this.roleService.queryRuleData();
    }

    /**
     *
     * 获取角色列表请求
     */
    @PostMapping("/access/user-role/list")
    @ResponseBody
    public ResultDO queryUserRole(PageVo pageVo){
        return this.accessService.queryAllUserRoleList(pageVo);
    }
    /**
     *
     * 更改用户角色请求
     */
    @PostMapping("/access/change")
    @ResponseBody
    public ResultDO changeUserRole(String adminName, String adminId, String roleStr){

        return this.accessService.changeUserRole(adminName,adminId,roleStr);
    }


}
