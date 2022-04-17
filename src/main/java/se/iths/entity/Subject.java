package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @ManyToOne
    private Teacher teacher;

    @ManyToMany
    private List<Student> students = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String subject;

    public Subject() {
    }

    public Subject(String subject, List<Student> students, Teacher teacher) {
        this.subject = subject;
        this.students = students;
        this.teacher = teacher;
    }


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public Teacher getTeacher() { return teacher; }

    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public List<Student> getStudents() { return students; }

    public void addStudent(Student student) { students.add(student); }

    public void setStudents(List<Student> studentsInSubject) { this.students = studentsInSubject; }


}
