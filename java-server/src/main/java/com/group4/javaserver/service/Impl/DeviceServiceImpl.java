package com.group4.javaserver.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group4.javaserver.dao.DeviceMapper;
import com.group4.javaserver.service.DeviceService;
import com.group4.javaserver.utils.DataValidator;
import com.group4.javaserver.dto.Response;
import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.ConditionVo;
import com.group4.javaserver.pojo.Device;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * (Device)表服务实现类
 *
 * @author 
 * @since 2020-06-17 15:44:43
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    @Autowired
    private DataValidator dataValidator;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Device queryById(Long id) {
        return this.deviceMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Device> queryAllByLimit(int offset, int limit) {
        return this.deviceMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    @Override
    public ResultDO insert(Device device) {
        //做数据校验
        dataValidator.validate(device);

        //判断对应设备是否存在
        Device dbDevice = this.deviceMapper.queryByBoardId(device.getBoardId());
        if(dbDevice != null){
            //存在就更新
            dbDevice.setComputerName(dbDevice.getComputerName());
            dbDevice.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            this.deviceMapper.update(dbDevice);
        }else {//不存在就添加

            //处理创建时间
            device.setCreateTime(new Timestamp(System.currentTimeMillis()));

            this.deviceMapper.insert(device);

        }
        return Response.success("添加成功！");
    }

    /**
     * 设备列表
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
        List<Device> list = deviceMapper.queryAll(conditionVo);

        //对数据进行pageInfo包装
        PageInfo<Device> pageInfo = new PageInfo<>(list);

        return Response.success("获取数据成功",pageInfo);
    }

    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    @Override
    public Device update(Device device) {
        this.deviceMapper.update(device);
        return this.queryById(device.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.deviceMapper.deleteById(id) > 0;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public ResultDO deleteAll(String[] ids) {

        deviceMapper.deleteAllByPk(ids);
        return Response.success("删除设备成功");

    }
}