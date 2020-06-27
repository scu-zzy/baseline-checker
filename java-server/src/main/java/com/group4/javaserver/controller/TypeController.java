package com.group4.javaserver.controller;

import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.ConditionVo;
import com.group4.javaserver.pojo.Type;
import com.group4.javaserver.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * (Type)表控制层
 *
 * @author 
 * @since 2020-06-18 10:09:24
 */
@Controller
@RequestMapping("type")
public class TypeController {
    /**
     * 服务对象
     */
    @Autowired
    private TypeService typeService;

    /**
     * 列表页面
     * @return
     */
    @GetMapping("/list/page")
    public String listPage(){
        return "type/type-list";
    }

    /**
     * 列表数据
     * @param conditionVo
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public ResultDO list(ConditionVo conditionVo){
        return typeService.list(conditionVo);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Type selectOne(Integer id) {
        return this.typeService.queryById(id);
    }

}