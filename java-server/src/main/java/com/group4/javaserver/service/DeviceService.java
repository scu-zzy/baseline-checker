package com.group4.javaserver.service;

import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.ConditionVo;
import com.group4.javaserver.pojo.Device;
import java.util.List;

/**
 * (Device)表服务接口
 *
 * @author 
 * @since 2020-06-17 15:44:43
 */
public interface DeviceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Device queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Device> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    ResultDO insert(Device device);

    /**
     * 设备列表
     * @param conditionVo
     * @return
     */
    ResultDO list(ConditionVo conditionVo);

    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 实例对象
     */
    Device update(Device device);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 批量删除
     * @param
     * @param ids
     * @return
     */
    ResultDO deleteAll(String[] ids);

}