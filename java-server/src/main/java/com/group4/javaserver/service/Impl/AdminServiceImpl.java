package com.group4.javaserver.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group4.javaserver.dao.*;
import com.group4.javaserver.dto.Response;
import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.exception.DataException;
import com.group4.javaserver.exception.LoginException;
import com.group4.javaserver.group.Add;
import com.group4.javaserver.group.Login;
import com.group4.javaserver.pojo.Admin;
import com.group4.javaserver.pojo.AdminVo;
import com.group4.javaserver.utils.DataValidator;
import com.group4.javaserver.utils.MD5Util;
import com.group4.javaserver.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * 管理员服务实现
 */
@Service
@SuppressWarnings("all")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private DataValidator dataValidator;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private ScanDataMapper scanDataMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private TypeMapper typeMapper;
    /**
     * 登录功能
     * @param admin
     * @param session
     * @return
     */
    @Override
    public ResultDO login(Admin admin, HttpSession session) throws LoginException {
        //数据验证（登录）
        dataValidator.validate(admin, Login.class);

        Subject subject  = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                admin.getAdminName(),
                MD5Util.MD55(admin.getAdminPwd())
        );
        try {
            subject.login(token);

        }catch (LoginException e){
            return Response.error(e.getCode(),e.getMsg());
        }

        //通过名字查询数据
        Admin dbAdmin = adminMapper.findByName(admin.getAdminName());

        //判断用户是否存在
        //if(dbAdmin == null){
            //return new ResultDO(1001,"用户不存在");
        //    throw new LoginException(1001,"用户不存在");
        //}

        //判断用户的密码是否正确
        //if (!dbAdmin.getAdminPwd().equals(MD5Util.MD55(admin.getAdminPwd()))) {
            //return new ResultDO(1002,"密码不正确");
         //   throw new LoginException(1002,"密码不正确");
        //}

        //登录成功,将数据存在session中
        session.setAttribute("admin",dbAdmin);
        //return new ResultDO(200,"登录成功");
        //修改登录时间
        dbAdmin.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        adminMapper.update(dbAdmin);

        return Response.success("登陆成功");



    }

    /**
     * 管理员列表
     * @param
     * @return
     */
    @Override
    public ResultDO list(AdminVo adminVo) {

        //分页
        PageHelper.startPage(adminVo.getPage(),adminVo.getPageSize());

        // 条件处理
        if(!StringUtils.isEmpty(adminVo.getKeywords())){
            adminVo.setKeywords("%"+adminVo.getKeywords()+"%");
        }

        //查询数据库
        List<Admin> list = adminMapper.search(adminVo);

        //对数据进行pageInfo包装
        PageInfo<Admin> pageInfo = new PageInfo<>(list);

        return Response.success("获取数据成功",pageInfo);
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @Override
    public ResultDO add(Admin admin) {
        //数据校验
        dataValidator.validate(admin, Add.class);
        String name=admin.getAdminName();

        //查询数据库，当前用户是否存在
        Admin dbAdmin = adminMapper.findByName(admin.getAdminName());
        if(dbAdmin != null){
            throw new DataException(3002,"该用户已存在");
        }

        //初始化创建时间
        admin.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //处理密码
        admin.setAdminPwd(MD5Util.MD55(admin.getAdminPwd()));


        adminMapper.insert(admin);

        Long id=adminMapper.findByName(name).getId();
        userRoleMapper.addRoleByName(id,name,2);

        return Response.success("添加管理员成功");

    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public ResultDO deleteAll(String[] ids) {
        for(String id:ids){
            userRoleMapper.deleteAllRoleById(id);
        }
        adminMapper.deleteAllByPk(ids);
        return Response.success("删除管理员成功");

    }

    /**
     * 状态改变
     * @param id
     * @return
     */
    @Override
    public ResultDO updateStatus(Integer id) {

        adminMapper.updateStatus(id);
        return Response.success("管理员状态改变成功");

    }

    /**
     * 编辑管理员
     * @param admin
     * @return
     */
    @Override
    public ResultDO edit(Admin admin) {

        //查询数据库，当前用户是否存在
        Admin dbAdmin = adminMapper.findByName(admin.getAdminName());
        if(dbAdmin != null && dbAdmin.getId()!=admin.getId()){
            throw new DataException(3002,"该用户已存在");
        }

        //处理密码
        admin.setAdminPwd(MD5Util.MD55(admin.getAdminPwd()));


        adminMapper.update(admin);

        return Response.success("编辑管理员成功");

    }

    /**
     * 统计数量
     * @return
     */
    @Override
    public ResultDO count() {
        Integer[] num = new Integer[4];
        num[0] = adminMapper.count();
        num[1] = deviceMapper.count();
        num[2] = typeMapper.count();
        num[3] = scanDataMapper.count();

        return Response.success("统计数量成功", num);
    }
}
