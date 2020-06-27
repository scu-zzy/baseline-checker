package com.group4.javaserver.pojo;

public class UserRoleData {

    private Long adminId;

    private String adminName;

    private String RoleCodes;

    public UserRoleData() {
    }

    public UserRoleData(Long adminId, String adminName) {
        this.adminId = adminId;
        this.adminName = adminName;
    }

    public UserRoleData(Long adminId, String adminName, String roleCodes) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.RoleCodes = roleCodes;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getRoleCodes() {
        return RoleCodes;
    }

    public void setRoleCodes(String roleCodes) {
        RoleCodes = roleCodes;
    }
}
