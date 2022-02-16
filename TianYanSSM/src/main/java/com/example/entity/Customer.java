package com.example.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
@Component
public class Customer {
    @Id
    private Integer cid;

    @Column(name = "cusName")
    private String cusname;

    @Column(name = "Address")
    private String address;

    private String contact;

    @Column(name = "Tel")
    private String tel;

    @Column(name = "Email")
    private String email;

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", cusname='" + cusname + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
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
     * @return cusName
     */
    public String getCusname() {
        return cusname;
    }

    /**
     * @param cusname
     */
    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    /**
     * @return Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return Tel
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
     * @return Email
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
}