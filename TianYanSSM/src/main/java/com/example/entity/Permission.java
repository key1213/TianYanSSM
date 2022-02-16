package com.example.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Component
public class Permission {
    @Id
    @Column(name = "permissionId")
    private Integer permissionid;

    private String pname;

    private String purl;

    private Integer ismenu;

    @Column(name = "parentId")
    private Integer parentid;

    private String pinfo;

    private List<Permission> list;

    public List<Permission> getList() {
        return list;
    }

    public void setList(List<Permission> list) {
        this.list = list;
    }

    /**
     * @return permissionId
     */
    public Integer getPermissionid() {
        return permissionid;
    }

    /**
     * @param permissionid
     */
    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }

    /**
     * @return pname
     */
    public String getPname() {
        return pname;
    }

    /**
     * @param pname
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * @return purl
     */
    public String getPurl() {
        return purl;
    }

    /**
     * @param purl
     */
    public void setPurl(String purl) {
        this.purl = purl;
    }

    /**
     * @return ismenu
     */
    public Integer getIsmenu() {
        return ismenu;
    }

    /**
     * @param ismenu
     */
    public void setIsmenu(Integer ismenu) {
        this.ismenu = ismenu;
    }

    /**
     * @return parentId
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * @param parentid
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * @return pinfo
     */
    public String getPinfo() {
        return pinfo;
    }

    /**
     * @param pinfo
     */
    public void setPinfo(String pinfo) {
        this.pinfo = pinfo;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionid=" + permissionid +
                ", pname='" + pname + '\'' +
                ", purl='" + purl + '\'' +
                ", ismenu=" + ismenu +
                ", parentid=" + parentid +
                ", pinfo='" + pinfo + '\'' +
                ", list=" + list +
                '}';
    }
}