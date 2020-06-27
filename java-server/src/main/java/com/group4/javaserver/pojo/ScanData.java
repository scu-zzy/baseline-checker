package com.group4.javaserver.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.io.Serializable;

/**
 * (ScanData)实体类
 *
 * @author 
 * @since 2020-06-18 10:06:09
 */
public class ScanData implements Serializable {
    private static final long serialVersionUID = 367490327432597366L;
    
    private Long id;
    /**
    * 主板ID
    */
    @NotBlank(message = "主板ID不能为空")
    private String boardId;
    /**
    * 数据类型
    */
    private Integer typeCode;
    /**
    * 具体数据
    */
    @NotBlank(message = "数据不能为空")
    private String data;
    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 扫描数据所属类型
     */
    private Type type;

    public ScanData() {
    }

    public ScanData(String boardId, Integer typeCode, String data, Date createTime) {
        this.boardId = boardId;
        this.typeCode = typeCode;
        this.data = data;
        this.createTime = createTime;
    }


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}