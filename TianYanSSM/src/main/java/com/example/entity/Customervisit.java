package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.util.Date;
@Component
public class Customervisit {
    @Id
    private Integer id;

    private Integer cid;

    private Integer empid;

    private String content;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private String cusname;

    private String empname;

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
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

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Customervisit{" +
                "id=" + id +
                ", cid=" + cid +
                ", empid=" + empid +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", cusname='" + cusname + '\'' +
                ", empname='" + empname + '\'' +
                '}';
    }
}