package com.group4.javaserver.dao;

import com.group4.javaserver.pojo.Permission;
import com.group4.javaserver.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);
    /**
     * 获取用户权限
     * @param name
     */
    Permission queryByName(@Param("name") String name);

    /**
     * 通过实体作为筛选条件查询
     *
     *
     * @return 对象列表
     */
    List<Permission> queryAll();

    /**
     * 保存新的表项
     *
     * @param display
     */
    void save(@Param("display")String display);

}
