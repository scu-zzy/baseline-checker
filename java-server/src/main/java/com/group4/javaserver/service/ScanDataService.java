package com.group4.javaserver.service;

import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.ConditionVo;
import com.group4.javaserver.pojo.ScanData;
import java.util.List;

/**
 * (ScanData)表服务接口
 *
 * @author 
 * @since 2020-06-18 10:06:09
 */
public interface ScanDataService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ResultDO queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ScanData> queryAllByLimit(int offset, int limit);

    /**
     * 查询对应设备的扫描类型
     * @param boardId : 主板ID
     * @return
     */
    ResultDO queryScanDataTypeList(String boardId);

    /**
     *
     * @param conditionVo
     * @return
     */
    ResultDO queryAllByBoardIdAndTypeCode(ConditionVo conditionVo);

    /**
     * 新增数据
     *
     * @param scanData 实例对象
     * @return 实例对象
     */
    ResultDO insert(ScanData scanData);


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