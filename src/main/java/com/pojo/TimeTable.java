package com.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "timetable")
public class TimeTable {
    @Id
    private Integer timetableid;

    private Integer classesid;

    private Integer coursesid;

    public Integer getTimetableid() {
        return timetableid;
    }

    public void setTimetableid(Integer timetableid) {
        this.timetableid = timetableid;
    }

    public Integer getClassesid() {
        return classesid;
    }

    public void setClassesid(Integer classesid) {
        this.classesid = classesid;
    }

    public Integer getCoursesid() {
        return coursesid;
    }

    public void setCoursesid(Integer coursesid) {
        this.coursesid = coursesid;
    }
}