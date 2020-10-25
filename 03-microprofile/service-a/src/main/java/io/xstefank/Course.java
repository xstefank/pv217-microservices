package io.xstefank;

import java.util.List;

public class Course {

    private String name;
    private int studentsCount;
    private List<Student> students;

    public Course() {
    }

    public Course(String name, int studentsCount, List<Student> students) {
        this.name = name;
        this.studentsCount = studentsCount;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
            "name='" + name + '\'' +
            ", studentsCount=" + studentsCount +
            ", students=" + students +
            '}';
    }
}
