package com.pojo;

import javax.persistence.Id;

public class Courses {
    @Id
    private Integer coursesid;

    private String coursesname;

    private Integer teacherid;

    private Double cost;

    private Integer term;

    public Integer getCoursesid() {
        return coursesid;
    }

    public void setCoursesid(Integer coursesid) {
        this.coursesid = coursesid;
    }

    public String getCoursesname() {
        return coursesname;
    }

    public void setCoursesname(String coursesname) {
        this.coursesname = coursesname;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }
}