package com.group4.javaserver.pojo;

import java.io.Serializable;

/**
 * (Type)实体类
 *
 * @author 
 * @since 2020-06-18 10:09:24
 */
public class Type implements Serializable {
    private static final long serialVersionUID = -18465326329118842L;
    
    private Integer id;
    /**
    * 类型号
    */
    private Integer typeCode;
    /**
    * 类型名
    */
    private String typeName;

    public Type() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}