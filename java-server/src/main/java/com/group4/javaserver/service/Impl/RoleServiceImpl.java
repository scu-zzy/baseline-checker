package com.group4.javaserver.service.Impl;

import com.group4.javaserver.dao.RoleMapper;
import com.group4.javaserver.dao.RuleMapper;
import com.group4.javaserver.dto.Response;
import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.exception.DataException;
import com.group4.javaserver.pojo.Role;
import com.group4.javaserver.pojo.Rule;
import com.group4.javaserver.pojo.RuleData;
import com.group4.javaserver.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RuleMapper ruleMapper;

    public List<String> queryPermissionByRoleCode(int roleCode){
        List<Rule> rule=ruleMapper.queryByRoleCode(roleCode);
        List<String> result=new ArrayList<>();
        for(Rule i:rule){
            result.add(String.valueOf(i.getPermission()));
        }
        return result;
    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Long id) {
        return this.roleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return this.roleMapper.queryAllByLimit(offset, limit);
    }

    public ResultDO queryRuleData(){

        List<Role> roles = this.roleMapper.queryAll();

        List<RuleData> ruleDatas=new ArrayList<RuleData>();
        if(roles == null){
            throw new DataException(3003,"查询失败");
        }
        for(Role i :roles){
            ruleDatas.add(new RuleData(i,queryPermissionByRoleCode(i.getRoleCode())));
        }

        return Response.success("200",ruleDatas);

    }
    /**
     * 查询所有数据
     *
     *
     */
    @Override
    public ResultDO queryAll(){
        List<Role> roles = this.roleMapper.queryAll();
        if(roles == null){
            throw new DataException(3003,"查询失败");
        }

        return Response.success("200",roles);
    }

}
