package com.pojo;

import javax.persistence.Id;

/**
 * 教师表
 */
public class Teacher {
    @Id
    private Integer teacherid;

    private String teachername;

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }
}