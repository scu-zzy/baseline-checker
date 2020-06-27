package com.group4.javaserver.service;

import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.Admin;
import com.group4.javaserver.pojo.PageVo;

public interface AccessService {

    ResultDO queryAllList();

    ResultDO addNewItem(String permission);

    ResultDO queryAllUserRoleList(PageVo pageVo);

    public ResultDO queryUserRoleByName(Admin admin);

    public ResultDO changeUserRole(String adminName, String adminId, String roleStr);
}
