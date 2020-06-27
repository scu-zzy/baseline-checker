package com.group4.javaserver.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group4.javaserver.dao.TypeMapper;
import com.group4.javaserver.dto.Response;
import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.ConditionVo;
import com.group4.javaserver.pojo.Type;
import com.group4.javaserver.service.TypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Type)表服务实现类
 *
 * @author 
 * @since 2020-06-18 10:09:24
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Type queryById(Integer id) {
        return this.typeMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Type> queryAllByLimit(int offset, int limit) {
        return this.typeMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 列表
     * @param conditionVo
     * @return
     */
    @Override
    public ResultDO list(ConditionVo conditionVo) {

        //分页
        PageHelper.startPage(conditionVo.getPage(),conditionVo.getPageSize());

        // 条件处理
        if(!StringUtils.isEmpty(conditionVo.getKeywords())){
            conditionVo.setKeywords("%"+conditionVo.getKeywords()+"%");
        }

        //查询数据库
        List<Type> list = this.typeMapper.queryAll(conditionVo);

        //对数据进行pageInfo包装
        PageInfo<Type> pageInfo = new PageInfo<>(list);

        return Response.success("获取数据成功",pageInfo);
    }

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type insert(Type type) {
        this.typeMapper.insert(type);
        return type;
    }

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type update(Type type) {
        this.typeMapper.update(type);
        return this.queryById(type.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.typeMapper.deleteById(id) > 0;
    }
}