package com.group4.javaserver.dao;

import com.group4.javaserver.pojo.ConditionVo;
import com.group4.javaserver.pojo.ScanData;
import com.group4.javaserver.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ScanData)表数据库访问层
 *
 * @author 
 * @since 2020-06-18 10:06:09
 */
@Mapper
public interface ScanDataMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ScanData queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ScanData> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param
     * @return 对象列表
     */
    List<ScanData> queryAll(@Param("conditionVo") ConditionVo conditionVo);

    /**
     * 通过主板ID去查询相关的扫描类型
     * @param boardId : 主板ID
     * @return
     */
    List<Type> queryScanTypeByBoardId(@Param("boardId") String boardId);

    /**
     * 新增数据
     *
     * @param scanData 实例对象
     * @return 影响行数
     */
    int insert(ScanData scanData);

    /**
     * 修改数据
     *
     * @param scanData 实例对象
     * @return 影响行数
     */
    int update(ScanData scanData);

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