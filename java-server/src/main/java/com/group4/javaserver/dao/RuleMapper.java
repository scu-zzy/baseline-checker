package com.group4.javaserver.dao;

import com.group4.javaserver.pojo.ConditionVo;
import com.group4.javaserver.pojo.Role;
import com.group4.javaserver.pojo.Rule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleMapper {

    Role queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Rule> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);
    /**
     * 获取角色的权限信息
     *
     * @param roleCode
     * @return 对象列表
     */
    List<String> getPermissionByRoleCode(@Param("RoleCode")int roleCode);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param conditionVo 条件对象
     * @return 对象列表
     */
    List<Rule> queryAll(@Param("conditionVo") ConditionVo conditionVo);
    /**
     * 通过roleCode获取规则
     *
     * @param roleCode
     * @return Rule
     */
    List<Rule> queryByRoleCode(@Param("roleCode") int roleCode);



}
