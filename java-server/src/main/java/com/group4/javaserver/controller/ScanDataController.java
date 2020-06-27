package com.group4.javaserver.controller;

import com.group4.javaserver.dto.ResultDO;
import com.group4.javaserver.pojo.ConditionVo;
import com.group4.javaserver.service.ScanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * (ScanData)表控制层
 *
 * @author 
 * @since 2020-06-18 10:06:09
 */
@Controller
@RequestMapping("scan")
public class ScanDataController {
    /**
     * 服务对象
     */
    @Autowired
    private ScanDataService scanDataService;

    /**
     * 扫描类型页
     * @return
     */
    @GetMapping("/type/list/page")
    public String scanTypeListPage(){
        return "scandata/scan-type-list";
    }

    /**
     * 扫描类型的列表数据
     * @param boardId
     * @return
     */
    @PostMapping("/type/list")
    @ResponseBody
    public ResultDO scanTypeList(String boardId){
        return this.scanDataService.queryScanDataTypeList(boardId);
    }


    /**
     * 具体扫描类型列表页
     * @return
     */
    @GetMapping("/data/list/page")
    public String scanDataListPage(){
        return "scandata/scan-data-list";
    }

    @PostMapping("/data/list")
    @ResponseBody
    public ResultDO scanDataList(ConditionVo conditionVo){
        return scanDataService.queryAllByBoardIdAndTypeCode(conditionVo);
    }

    /**
     * 基本信息页
     *
     */
    @GetMapping("/data/detail/page/2")
    public String baseInfoPage(){
        return "scandata/base-info";
    }

    /**
     * 网络信息页
     *
     */
    @GetMapping("/data/detail/page/4")
    public String networkInfoPage(){
        return "scandata/network-info";
    }

    /**
     * 安装软件页
     *
     */
    @GetMapping("/data/detail/page/3")
    public String softInfoPage(){
        return "scandata/soft-info";
    }

    /**
     * 自启动项页
     *
     */
    @GetMapping("/data/detail/page/8")
    public String autorunsPage(){
        return "scandata/autoruns-info";
    }

    /**
     * 更新补丁信息
     *
     */
    @GetMapping("/data/detail/page/5")
    public String updateInfoPage(){
        return "scandata/update-info";
    }

    /**
     * 服务信息
     *
     */
    @GetMapping("/data/detail/page/6")
    public String serviceInfoPage(){
        return "scandata/service-info";
    }

    /**
     * 服务详情
     *
     */
    @GetMapping("/data/service/detail/page")
    public String serviceDetailInfoPage(){
        return "scandata/service-detail-info";
    }

    /**
     * 策略信息
     *
     */
    @GetMapping("/data/detail/page/7")
    public String policyInfoPage(){
        return "scandata/policy-info";
    }

    /**
     * 策略详情
     *
     */
    @GetMapping("/data/policy/detail/page")
    public String policyDetailInfoPage(){
        return "scandata/policy-detail-info";
    }

    /**
     * 批量删除
     * @param
     * @return
     */
    @PostMapping("/data/delete")
    @ResponseBody
    public ResultDO deviceDeleteAll(@RequestParam("ids[]") String[] ids){
        return scanDataService.deleteAll(ids);
    }

    /**
     * 数据详细信息
     * @param id
     * @return
     */
    @PostMapping("/data/detail")
    @ResponseBody
    public ResultDO detail(Long id){
        return this.scanDataService.queryById(id);
    }



}