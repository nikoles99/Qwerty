package com.example.admin.qwerty;

/**
 * Created by Admin on 05.04.2015.
 */
public class Timetable {
    String time,subject,lastname,type,audience,sunday;

    public Timetable(String time, String subject, String lastname, String type, String audience) {
        this.time = time;
        this.subject = subject;
        this.lastname = lastname;
        this.type = type;
        this.audience = audience;
    }
    public Timetable(String sunday){
        this.sunday=sunday;
    }
    public String getSunday() {
        return sunday;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
