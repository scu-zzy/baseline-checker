package com.group4.javaserver.pojo;

public class Role {

    private Long id;
    private String roleName;
    private String roleDisplay;

    private Integer roleState;
    private Integer roleCode;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public String getRoleDisplay() {
        return roleDisplay;
    }

    public void setRoleDisplay(String roleDisplay) {
        this.roleDisplay = roleDisplay;
    }


    public Integer getRoleStatus() {
        return roleState;
    }

    public void setRoleStatus(Integer RoleStatus) {
        this.roleState = roleState;
    }


    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }


}
