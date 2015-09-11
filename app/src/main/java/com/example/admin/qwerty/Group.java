package com.example.admin.qwerty;

/**
 * Описание группы
 *
 * @author OlesiukNV
 */
public class Group {
    private int id;
    private int course;
    private int facultyId;
    private int specialityDepartmentEducationFormId;
    private String name;

    public Group() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setSpecialityDepartmentEducationFormId(int specialityDepartmentEducationFormId) {
        this.specialityDepartmentEducationFormId = specialityDepartmentEducationFormId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public int getCourse() {
        return course;
    }

    public int getSpecialityDepartmentEducationFormId() {
        return specialityDepartmentEducationFormId;
    }

    public String getName() {
        return name;
    }
}
