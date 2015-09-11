package com.example.admin.qwerty;

import android.text.format.Time;

/**
 * Описание учебной дисцеплины
 */
public class Lesson {
    /**
     * Время начала занятия
     */
    private Time startTime;

    /**
     * Время окончания занятия
     */
    private Time finishTime;

    /**
     * Номер учебной недели
     */
    private int weekNumber;

    /**
     * Номер подгруппы
     */
    private int subGroup;

    /**
     * Название дисцеплины
     */
    private String name;

    /**
     * Тип проведения занятия
     */
    private String type;

    /**
     * Аудитория
     */
    private String audience;

    /**
     * Преподователь
     */
    private Teacher teacher;

    public Lesson(Time startTime, Time finishTime, int weekNumber, int subGroup, String name, String type, String audience, Teacher teacher) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.weekNumber = weekNumber;
        this.subGroup = subGroup;
        this.name = name;
        this.type = type;
        this.audience = audience;
        this.teacher = teacher;
    }

    public Time getStartTime() {
        return startTime;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public Time getFinishTime() {
        return finishTime;
    }

    public int getSubGroup() {
        return subGroup;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAudience() {
        return audience;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
