package com.group4.javaserver.pojo;

public class UserRole {


        private Long id;
        private Long adminId;
        private String adminName;
        private Integer roleCode;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public void setRoleName(String adminName) {
            this.adminName = adminName;
        }


        public Integer getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(Integer roleCode) {
            this.roleCode = roleCode;
        }


}
