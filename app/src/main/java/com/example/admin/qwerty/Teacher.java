package com.example.admin.qwerty;

/**
 * Описание преподователя
 *
 * @author OlesiykNV
 */
public class Teacher {

    /**
     * Имя
     */
    private String name;

    /**
     * Отчество
     */
    private String middleName;

    /**
     * Фамилия
     */
    private String surname;

    public Teacher(String name, String middleName, String surname) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
