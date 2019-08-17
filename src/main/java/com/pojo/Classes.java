package com.pojo;

import javax.persistence.Id;

/**
 * 班级表
 */
public class Classes {
    @Id
    private Integer classesid;

    private Integer classesname;

    public Integer getClassesid() {
        return classesid;
    }

    public void setClassesid(Integer classesid) {
        this.classesid = classesid;
    }

    public Integer getClassesname() {
        return classesname;
    }

    public void setClassesname(Integer classesname) {
        this.classesname = classesname;
    }
}