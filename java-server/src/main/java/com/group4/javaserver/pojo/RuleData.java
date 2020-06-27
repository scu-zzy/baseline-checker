package com.group4.javaserver.pojo;

import java.util.List;

public class RuleData {

    private Long id;

    private Integer roleState;

    private String roleName;

    private String roleDisplay;

    private String perstr;

    public RuleData(){

    }
    public RuleData(Role role, List<String> permissions){
        this.id=role.getId();
        this.roleState=role.getRoleStatus();
        this.roleName=role.getRoleName();
        this.roleDisplay=role.getRoleDisplay();

        perstr="";
        for(String i:permissions){
            perstr=perstr+i;
            if(i!=permissions.get(permissions.size()-1)){
                perstr=perstr+",";
            }
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoleState() {
        return roleState;
    }

    public void setRoleCode(Integer roleState) {
        this.roleState = roleState;
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

    public String getPerStr() {
        return perstr;
    }

    public void setPerStr(String perstr) {
        this.perstr = perstr;
    }

}
