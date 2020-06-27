package com.group4.javaserver.controller;

import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.ConditionVo;
import com.group4.javaserver.pojo.Device;
import com.group4.javaserver.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Device)表控制层
 *
 * @author 
 * @since 2020-06-17 15:44:43
 */
@Controller
@RequestMapping("device")
public class DeviceController {
    /**
     * 服务对象
     */
    @Resource
    private DeviceService deviceService;

    /**
     * 设备列表页面
     * @return
     */
    @GetMapping("/list/page")
    public String listPage(){
        return "device/device-list";
    }

    /**
     * 列表数据
     * @param conditionVo
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public ResultDO list(ConditionVo conditionVo){
        return deviceService.list(conditionVo);
    }

    /**
     * 批量删除
     * @param
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultDO deviceDeleteAll(@RequestParam("ids[]") String[] ids){
        return deviceService.deleteAll(ids);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Device selectOne(Long id) {
        return this.deviceService.queryById(id);
    }

}