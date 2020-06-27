package com.group4.javaserver.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.javaserver.pojo.Device;
import com.group4.javaserver.pojo.PyData;
import com.group4.javaserver.pojo.ScanData;
import com.group4.javaserver.service.DeviceService;
import com.group4.javaserver.service.ScanDataService;
import com.group4.javaserver.pojo.TypeConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * 解析传递的数据
 */
@Service
public class ProcessDataService {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ScanDataService scanDataService;

    /**
     * 解析并处理数据
     * @param data
     */
    public void process(String data) throws JsonProcessingException {
        //解析data，转换成Java的PyData
        PyData pyData = getPyData(data);
        //判断类型(是否为注册）
        if(TypeConst.REGISTER.equals(pyData.getTypeCode())){//插入device数据表中

            //从PyData中获取字符串
            String deviceData = pyData.getData();
            //将单引号替换成双引号
            deviceData = deviceData.replaceAll("'","\"");
            //解析deviceData，转换为Device对象
            Device device = (Device) getEntityData(deviceData, Device.class);
            //入库
            deviceService.insert(device);

        }else {//其余数据进入scan_data数据表中
            System.out.println(pyData);
            ScanData scanData = new ScanData(pyData.getBoardId(), pyData.getTypeCode(), pyData.getData(), new Timestamp(System.currentTimeMillis()));
            scanDataService.insert(scanData);
        }

    }

    /**
     * 解析传递的数据
     * @param data
     * @return
     * @throws JsonProcessingException
     */
    private PyData getPyData(String data) throws JsonProcessingException {
        //利用jackson进行数据的解析
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, PyData.class);
    }

    /**
     * 解析数据转换为实体对象
     * @param data
     * @param clazz
     * @return
     * @throws JsonProcessingException
     */
    public Object getEntityData(String data, Class clazz) throws JsonProcessingException {
        //利用jackson进行数据的解析
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, clazz);
    }
}
