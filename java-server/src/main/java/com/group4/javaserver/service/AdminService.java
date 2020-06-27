package com.group4.javaserver.service;

import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.exception.LoginException;
import com.group4.javaserver.pojo.Admin;
import com.group4.javaserver.pojo.AdminVo;

import javax.servlet.http.HttpSession;

/**
 * 管理员服务
 */
public interface AdminService {
    /**
     * 登录方法
     * @param admin
     * @param session
     * @return
     */
    ResultDO login(Admin admin, HttpSession session) throws LoginException;

    /**
     * 管理员列表
     * @param
     * @return
     */
    ResultDO list(AdminVo adminVo);

    /**
     * 管理员添加
     * @param admin
     * @return
     */
    ResultDO add(Admin admin);

    /**
     * 批量删除
     * @param
     * @param ids
     * @return
     */
    ResultDO deleteAll(String[] ids);

    /**
     * 统计数量
     *
     * @return
     */
    ResultDO count();

    /**
     * 状态改变
     * @param
     * @param id
     * @return
     */
    ResultDO updateStatus(Integer id);

    /**
     * 管理员编辑
     * @param admin
     * @return
     */
    ResultDO edit(Admin admin);

}
