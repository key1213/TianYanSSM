package com.example.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RolePermission {
    private Integer roleId;
    private Integer id;
    private Integer permissionId;
    private String roleName;
    private String pinfo;
    private List<Integer> list;

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPinfo() {
        return pinfo;
    }

    public void setPinfo(String pinfo) {
        this.pinfo = pinfo;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "roleId=" + roleId +
                ", id=" + id +
                ", permissionId=" + permissionId +
                ", roleName='" + roleName + '\'' +
                ", pinfo='" + pinfo + '\'' +
                ", list=" + list +
                '}';
    }
}
