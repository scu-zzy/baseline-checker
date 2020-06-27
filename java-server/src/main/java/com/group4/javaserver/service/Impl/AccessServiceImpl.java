package com.group4.javaserver.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group4.javaserver.dao.AdminMapper;
import com.group4.javaserver.dao.PermissionMapper;
import com.group4.javaserver.dao.UserRoleMapper;
import com.group4.javaserver.dto.Response;
import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.exception.DataException;
import com.group4.javaserver.pojo.*;
import com.group4.javaserver.service.AccessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("accessService")
public class AccessServiceImpl implements AccessService {

    @Resource
    private PermissionMapper perMapper;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    public ResultDO queryAllList() {

        List <Permission> permissions = perMapper.queryAll();

        return Response.success("获取数据成功!",permissions);
    }
    public ResultDO addNewItem(String permission){
        if(permission!=null) {
            Permission dbPermission = perMapper.queryByName(permission);
            if (dbPermission != null) {
                // 说明权限已经存在
                throw new DataException(3002, "该权限已经存在!");

            } else {
                perMapper.save(permission);
                return Response.success("添加管理员成功!");
            }
        }else{
            return Response.error(3001,"添加错误!");
        }
    }

    public ResultDO queryAllUserRoleList(PageVo pageVo){



        List<UserRole> userRoles = userRoleMapper.queryAll();
        PageHelper.startPage(pageVo.getPage(),pageVo.getPageSize());

        List<UserRoleData> userRoleDatas = new ArrayList<UserRoleData>();

        for (UserRole userRole:userRoles) {

            if(!hasIt(userRoleDatas,userRole)){
                userRoleDatas.add(new UserRoleData(userRole.getAdminId(),
                        userRole.getAdminName(),collect(userRole)));
            }
        }

        PageInfo<UserRoleData> pageInfo = new PageInfo<>(userRoleDatas);
        return Response.success("查询成功！",pageInfo);
    }

    /**
     * 查询相同名字的所有数据
     * @param userRole
     * @return
     */
    public String collect(UserRole userRole){

        List<UserRole> userRoles = userRoleMapper.getItemsByName(userRole.getAdminName());
        String string = "";

        for(UserRole userRole0 : userRoles){
            string += ",";
            string += String.valueOf(userRole0.getRoleCode());
        }

        string = string.substring(1,string.length());

        return string;

    }
    /**
     * 判断是否已经创建
     * @param userRoleDatas
     * @param userRole
     * @return true——创建，false——未创建
     */
    public boolean hasIt(List<UserRoleData> userRoleDatas, UserRole userRole){

        for(UserRoleData userRoleData:userRoleDatas){
            if(userRole.getAdminId() == userRoleData.getAdminId()){
                return true;
            }
        }

        return false;

    }

    public ResultDO queryUserRoleByName(Admin admin){

        List<UserRole> userRoles = userRoleMapper.queryRoleCodeByName(admin.getAdminName());

        return Response.success("查询成功！",userRoles);
    }

    public ResultDO changeUserRole(String adminName, String adminId, String roleStr){
        if(roleStr==""){
            return Response.error(3000,"选择不能为空");

        }
        String[] roletemp=roleStr.split(",");
        Set<String> roleset=new HashSet<>();
        roleset.addAll(Arrays.asList(roletemp));

        Admin dbAdmin=adminMapper.findByName(adminName);
        if(dbAdmin==null){

            throw new DataException(3001, "该用户不存在!");

        }
        Set<String> userroles=new HashSet<String>();
        List<UserRole> roles=userRoleMapper.getItemsByName(adminName);
        for(UserRole role:roles){
            userroles.add(String.valueOf(role.getRoleCode()));
        }
        Set<String> tempSet=new HashSet<String>();
        tempSet.addAll(roleset);
        tempSet.removeAll(userroles);
        for(String addItem:tempSet){
            userRoleMapper.addRoleByName(Long.valueOf(adminId),adminName,Integer.valueOf(addItem));
        }
        tempSet.clear();
        tempSet.addAll(userroles);
        tempSet.removeAll(roleset);
        for(String deleteItem:tempSet){
            userRoleMapper.deleteRoleByName(adminName,Integer.valueOf(deleteItem));
        }


        return Response.success("修改成功！");
    }
}
