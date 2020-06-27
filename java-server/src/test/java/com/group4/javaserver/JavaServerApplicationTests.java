package com.group4.javaserver;

import com.group4.javaserver.dao.AdminMapper;
import com.group4.javaserver.pojo.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class JavaServerApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 注入mapper对象
     */
    @Autowired
    private AdminMapper adminMapper;



    @Test
    void testUpdate(){
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setAdminName("Zzy2");
        adminMapper.update(admin);
    }

    @Test
    void testInsert(){
        Admin admin = new Admin();
        admin.setAdminName("zzy3");
        admin.setAdminPwd("123");
        admin.setAdminPhone(2113L);
        admin.setCreateTime(new Timestamp(System.currentTimeMillis()));
        adminMapper.insert(admin);
    }

    @Test
    void testFind(){
        adminMapper.findById(2L);
        adminMapper.findByName("zzy");
    }
}
