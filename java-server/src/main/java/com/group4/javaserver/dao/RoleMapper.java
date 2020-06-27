package com.group4.javaserver.dao;

import com.group4.javaserver.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    /**
     * 通过roleCode查询单条数据
     *
     * @param roleCode
     * @return 实例对象
     */
    Role queryByCode(@Param("roleCode")Long roleCode);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(@Param("id")Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param
     * @return 对象列表
     */
    List<Role> queryAll();

    /**
     * 获取所有的角色列表
     *
     * @param
     * @return rolelist
     */
    List<String> getRoleList();


}
