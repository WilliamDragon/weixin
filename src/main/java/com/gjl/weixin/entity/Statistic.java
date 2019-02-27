package com.gjl.weixin.entity;

import java.util.Date;

public class Statistic {
    private Long id;

    private Date createTime;

    private Long studentId;

    private Long pxclassId;

    private Long questionId;

    private String answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getPxclassId() {
        return pxclassId;
    }

    public void setPxclassId(Long pxclassId) {
        this.pxclassId = pxclassId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", studentId=" + studentId +
                ", pxclassId=" + pxclassId +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                '}';
    }
}