package com.group4.javaserver.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.group4.javaserver.group.Login;
import com.group4.javaserver.group.Add;

import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * 管理员实体类
 */
public class Admin {

  private Long id;
  @NotBlank(message = "管理员账户不能为空", groups = {Add.class, Login.class})
  private String adminName;
  @NotBlank(message = "管理员密码不能为空", groups = {Add.class, Login.class})
  private String adminPwd;
  @NotNull(message = "手机号不能为空", groups = {Add.class})
  private Long adminPhone;
  private Integer adminStatus;
  private Integer isDeleted;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Timestamp createTime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Timestamp lastLoginTime;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }


  public String getAdminPwd() {
    return adminPwd;
  }

  public void setAdminPwd(String adminPwd) {
    this.adminPwd = adminPwd;
  }


  public Long getAdminPhone() {
    return adminPhone;
  }

  public void setAdminPhone(Long adminPhone) {
    this.adminPhone = adminPhone;
  }


  public Integer getAdminStatus() {
    return adminStatus;
  }

  public void setAdminStatus(Integer adminStatus) {
    this.adminStatus = adminStatus;
  }


  public Integer getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Integer isDeleted) {
    this.isDeleted = isDeleted;
  }


  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }


  public Timestamp getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Timestamp lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

}
