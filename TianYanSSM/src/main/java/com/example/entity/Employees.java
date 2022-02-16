package com.example.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
@Component
public class Employees {
    @Id
    private Integer empid;

    private String username;
    
    private String password;

    private String tel;

    private String name;

    private String email;

    private String boss;

    private String roleName;

    private Integer emptype;

    @Column(name = "roleId")
    private Integer roleid;


    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return empid
     */
    public Integer getEmpid() {
        return empid;
    }

    /**
     * @param empid
     */
    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return emptype
     */
    public Integer getEmptype() {
        return emptype;
    }

    /**
     * @param emptype
     */
    public void setEmptype(Integer emptype) {
        this.emptype = emptype;
    }

    /**
     * @return roleId
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "empid=" + empid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", boss='" + boss + '\'' +
                ", roleName='" + roleName + '\'' +
                ", emptype=" + emptype +
                ", roleid=" + roleid +
                '}';
    }
}