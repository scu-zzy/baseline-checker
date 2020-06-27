package com.group4.javaserver.pojo;

import java.io.Serializable;

/**
 * (Rule)实体类
 *
 * @author makejava
 * @since 2020-06-24 13:32:34
 */
public class Rule implements Serializable {
    private static final long serialVersionUID = 413421504085061296L;
    
    private Integer id;
    
    private Integer roleCode;
    
    private Integer permission;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

}