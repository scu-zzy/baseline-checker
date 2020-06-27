package com.group4.javaserver.controller;

import com.group4.javaserver.dto.Response;
import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.Admin;
import com.group4.javaserver.pojo.AdminVo;
import com.group4.javaserver.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 *
 * 后台控制器
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;


    /**
     *
     * 后台首页
     */
    @GetMapping({"/","/index"})
    public String index() {
        return "index";
    }

    /**
     * 欢迎页
     */
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 登录页
     */
    @GetMapping("/login/page")
    public String loginPage(){
        return "login";
    }

    /**
     * 管理员添加页
     * @return
     */
    @GetMapping("/admin/add/page")
    public String adminAddPage(){
        return "admin/admin-add";
    }

    /**
     *
     * @param admin
     * @return
     */
    @PostMapping("/admin/add")
    @ResponseBody
    public ResultDO adminAdd(Admin admin){
        return adminService.add(admin);
    }

    /**
     * 管理员列表页
     * @return
     */
    @GetMapping("/admin/list/page")
    public String adminListPage(){
        return "admin/admin-list";
    }

    /**
     * 获取管理员列表
     * @return
     */
    @PostMapping("/admin/list")
    @ResponseBody
    public ResultDO adminList(AdminVo adminVo){
        return adminService.list(adminVo);
    }

    /**
     * 统计数量
     * @return
     */
    @PostMapping("/count")
    @ResponseBody
    public ResultDO count(){
        return adminService.count();
    }

    /**
     * 批量删除
     * @param
     * @return
     */
    @PostMapping("/admin/delete")
    @ResponseBody
    public ResultDO adminDeleteAll(@RequestParam("ids[]") String[] ids){
        return adminService.deleteAll(ids);
    }

    /**
     * 登录的方法
     * @param admin
     * @return
     */
    @PostMapping("/login/do")
    @ResponseBody
    public ResultDO login(Admin admin, HttpSession session){
        return adminService.login(admin, session);
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @PostMapping("/logout")
    @ResponseBody
    public ResultDO logout(HttpSession session){
        session.setAttribute("admin",null);
        return Response.success();
    }

    /**
     * 状态改变
     * @param
     * @return
     */
    @PostMapping("/admin/status")
    @ResponseBody
    public ResultDO adminUpdateStatus(@RequestParam("id") Integer id){
        return adminService.updateStatus(id);
    }

    /**
     * 管理员编辑页
     * @return
     */
    @GetMapping("/admin/edit/page")
    public String adminEditPage(){
        return "admin/admin-edit";
    }

    /**
     *
     * @param admin
     * @return
     */
    @PostMapping("/admin/edit")
    @ResponseBody
    public ResultDO adminEdit(Admin admin){ return adminService.edit(admin); }
    /**
     * 角色信息页
     * @return
     */
    @GetMapping("/admin/role/page")
    public String adminRolePage(){
        return "admin/admin-role";
    }
    /**
     * 角色管理页
     * @return
     */
    @GetMapping("/admin/rule/page")
    public String adminRulePage(){
        return "admin/admin-rule";
    }
    /**
     * 权限信息页
     * @return
     */
    @GetMapping("/admin/cate/page")
    public String adminCatePage(){
        return "admin/admin-cate";
    }
    /**
     * 无权限页
     * @return
     */
    @GetMapping("/notfound/page")
    public String NotFoundPage(){
        return "notfound";
    }

}
