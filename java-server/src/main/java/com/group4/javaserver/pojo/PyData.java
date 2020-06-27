package com.group4.javaserver.pojo;

/**
 * 用来解析python传递的数据
 */
public class PyData {

    private Integer typeCode;

    private String data;

    /**
     * 主板ID：不同机器的标识
     */
    private String boardId;

    public PyData() {
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

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    @Override
    public String toString() {
        return "PyData{" +
                "typeCode=" + typeCode +
                ", data='" + data + '\'' +
                ", boardId='" + boardId + '\'' +
                '}';
    }

}
