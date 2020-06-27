package com.group4.javaserver.service;

import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.Role;

import java.util.List;

public interface RoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(int offset, int limit);


    /**
     * 查询所有数据
     *
     *
     */
    ResultDO queryAll();
    public ResultDO queryRuleData();
//    /**
//     * 新增数据
//     *
//     * @param role 实例对象
//     * @return 实例对象
//     */
//    Role insert(Role role);
//
//    /**
//     * 修改数据
//     *
//     * @param role 实例对象
//     * @return 实例对象
//     */
//    Role update(Role role);
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 是否成功
//     */
//    boolean deleteById(Object id);

}
