package com.group4.javaserver.dao;

import com.group4.javaserver.pojo.Admin;
import com.group4.javaserver.pojo.AdminVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    /**
     * 条件查询
     *
     * @return
     */
    List<Admin> search(@Param("adminVo") AdminVo adminVo);

    /**
     * 修改
     * @param admin
     */
    void update(@Param("admin") Admin admin);

    /**
     * 插入
     * @param admin
     */
    void insert(@Param("admin") Admin admin);


    /**
     * 通过id查找
     * @param id
     * @return
     */
    Admin findById(@Param("id") Long id);

    /**
     * 通过名字查询
     * @param name
     * @return
     */
    Admin findByName(@Param("name") String name);

    /**
     *
     * 批量删除
     * @param ids
     */
    void  deleteAllByPk(@Param("ids") String[] ids);

    /**
     *
     * 状态改变
     * @param id
     */
    void updateStatus(@Param("id") Integer id);

    /**
     * 统计数量
     * @return
     */
    Integer count();

}
