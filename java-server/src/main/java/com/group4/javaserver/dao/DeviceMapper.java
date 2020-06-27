package com.group4.javaserver.dao;

import com.group4.javaserver.pojo.ConditionVo;
import com.group4.javaserver.pojo.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Device)表数据库访问层
 *
 * @author 
 * @since 2020-06-17 15:44:43
 */
@Mapper
public interface DeviceMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Device queryById(Long id);

    /**
     * 通过主板ID查询
     * @param boardId
     * @return
     */
    Device queryByBoardId(@Param("boardId") String boardId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Device> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param
     * @return 对象列表
     */
    List<Device> queryAll(@Param("conditionVo") ConditionVo conditionVo);

    /**
     * 新增数据
     *
     * @param device 实例对象
     * @return 影响行数
     */
    int insert(Device device);

    /**
     * 修改数据
     *
     * @param device 实例对象
     * @return 影响行数
     */
    int update(Device device);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     *
     * 批量删除
     * @param ids
     */
    void  deleteAllByPk(@Param("ids") String[] ids);

    /**
     * 统计数量
     * @return
     */
    Integer count();

}