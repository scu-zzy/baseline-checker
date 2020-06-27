package com.group4.javaserver.dao;

import com.group4.javaserver.pojo.Role;
import com.group4.javaserver.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRoleMapper {
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
    List<UserRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);
    /**
     * 通过实体作为筛选条件查询
     *
     *
     * @return 对象列表
     */
    List<UserRole> queryAll();
    /**
     * 通过adminName获取所有的UserRole
     *
     * @param adminName
     * @return 实例对象
     */
    List<UserRole> getItemsByName(@Param("adminName") String adminName);
    /**
     * 通过adminName获取所有的UserRole
     *
     * @param adminName
     * @return 实例对象
     */
    List<UserRole> queryRoleCodeByName(@Param("adminName") String adminName);
    /**
     * 删除指定的表项
     *
     * @param adminName
     * @param roleCode
     * @return void
     */
    void deleteRoleByName(@Param("adminName") String adminName, @Param("roleCode") Integer roleCode);

    /**
     * 删除指定的表项
     *

     * @param adminId
     * @return void
     */
    void deleteAllRoleById(@Param("adminId") String adminId);
    /**
     * 增添指定的表项
     *
     * @param adminId
     * @param adminName
     * @param roleCode
     * @return void
     */
    void addRoleByName(@Param("adminId")Long adminId, @Param("adminName") String adminName, @Param("roleCode") Integer roleCode);
    /**
     * 保存新的表项
     *
     * @param adminName
     */
    void save(@Param("adminId")Long adminId, @Param("adminName")String adminName);
}
