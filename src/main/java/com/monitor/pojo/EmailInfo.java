package com.monitor.pojo;

import java.io.Serializable;

/**
 * 简单的邮件分装类
 * @author Created by Divo
 * @date 2019/11/20
 */
public class EmailInfo implements Serializable {
    private String From;
    private String To;
    private String Subject;
    private String msg;

    public EmailInfo() {
    }

    public EmailInfo(String from, String to, String subject, String msg) {
        From = from;
        To = to;
        Subject = subject;
        this.msg = msg;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
