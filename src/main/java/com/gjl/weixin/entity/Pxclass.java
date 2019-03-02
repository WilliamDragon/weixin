package com.gjl.weixin.entity;

import java.util.Date;

public class Pxclass {
    private Long id;

    private String category;

    private String className;

    private String classPerson;

    private Date endTime;

    private String number;

    private String professionPerson;

    private Date startTime;

    private String teachePerson;

    private String day;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getClassPerson() {
        return classPerson;
    }

    public void setClassPerson(String classPerson) {
        this.classPerson = classPerson == null ? null : classPerson.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getProfessionPerson() {
        return professionPerson;
    }

    public void setProfessionPerson(String professionPerson) {
        this.professionPerson = professionPerson == null ? null : professionPerson.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getTeachePerson() {
        return teachePerson;
    }

    public void setTeachePerson(String teachePerson) {
        this.teachePerson = teachePerson == null ? null : teachePerson.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }
}