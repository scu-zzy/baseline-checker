package com.group4.javaserver.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group4.javaserver.dao.ScanDataMapper;
import com.group4.javaserver.dao.TypeMapper;
import com.group4.javaserver.dto.Response;
import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.ConditionVo;
import com.group4.javaserver.pojo.ScanData;
import com.group4.javaserver.pojo.Type;
import com.group4.javaserver.service.ScanDataService;
import com.group4.javaserver.utils.DataValidator;
import com.group4.javaserver.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ScanData)表服务实现类
 *
 * @author 
 * @since 2020-06-18 10:06:09
 */
@Service("scanDataService")
public class ScanDataServiceImpl implements ScanDataService {

    @Resource
    private ScanDataMapper scanDataMapper;

    @Autowired
    private DataValidator dataValidator;

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ResultDO queryById(Long id) {
        ScanData scanData = this.scanDataMapper.queryById(id);
        return Response.success("获取数据成功",scanData);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ScanData> queryAllByLimit(int offset, int limit) {
        return this.scanDataMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param scanData 实例对象
     * @return 实例对象
     */
    @Override
    public ResultDO insert(ScanData scanData) {

        //数据校验
        dataValidator.validate(scanData);
        //检查提交的类型，通过typeCode去查询
        Type type = this.typeMapper.queryByTypeCode(scanData.getTypeCode());

        if(type == null){
            throw new DataException(5001,"类型不匹配");
        }

        this.scanDataMapper.insert(scanData);
        return Response.success("插入成功");
    }

    /**
     * 查询对应设备的扫描类型
     * @param boardId : 主板ID
     * @return
     */
    @Override
    public ResultDO queryScanDataTypeList(String boardId) {
        if(boardId == null){
            throw new DataException(3009,"设备ID不能为空!");
        }
        List<Type> types = this.scanDataMapper.queryScanTypeByBoardId(boardId);
        return Response.success("获取数据成功!",types);
    }

    /**
     *
     * @param conditionVo
     * @return
     */
    @Override
    public ResultDO queryAllByBoardIdAndTypeCode(ConditionVo conditionVo) {
        //分页
        PageHelper.startPage(conditionVo.getPage(),conditionVo.getPageSize());

        //查询数据库
        List<ScanData> list = scanDataMapper.queryAll(conditionVo);

        //对数据进行pageInfo包装
        PageInfo<ScanData> pageInfo = new PageInfo<>(list);

        return Response.success("获取数据成功",pageInfo);
    }



    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.scanDataMapper.deleteById(id) > 0;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public ResultDO deleteAll(String[] ids) {

        scanDataMapper.deleteAllByPk(ids);
        return Response.success("删除数据成功");

    }
}