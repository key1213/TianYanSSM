package com.example.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Component
public class Customershare {
    @Id
    private Integer id;

    private Integer cid;

    private Integer empid;

    private Customer cus;

    private Employees emp;

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public Employees getEmp() {
        return emp;
    }

    public void setEmp(Employees emp) {
        this.emp = emp;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return cid
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(Integer cid) {
        this.cid = cid;
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

    @Override
    public String toString() {
        return "Customershare{" +
                "id=" + id +
                ", cid=" + cid +
                ", empid=" + empid +
                ", cus=" + cus +
                ", emp=" + emp +
                '}';
    }
}