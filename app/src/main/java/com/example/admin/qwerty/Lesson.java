package com.example.admin.qwerty;

public class Lesson {
    private String time;
    private String name;
    private String lastName;
    private String type;
    private String audience;

    public Lesson(String time, String name, String lastName, String type, String audience) {
        this.time = time;
        this.name = name;
        this.lastName = lastName;
        this.type = type;
        this.audience = audience;
    }

    public String getTime() {
        return time;
    }

    public String getAudience() {
        return audience;
    }

    public String getType() {
        return type;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }
}
