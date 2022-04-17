package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;
    //Create
    public void createSubject(Subject subject){
        entityManager.persist(subject);
    }
    //Remove, Delete
    public void removeSubject(Long subjectId) {
        entityManager.remove(entityManager.find(Subject.class, subjectId));
    }

    public void removeTeacherFromSubject(Long subjectId, Long teacherId){
        Subject subject = entityManager.find(Subject.class, subjectId);

        subject.setTeacher(null);
        entityManager.merge(subject);
    }

    public void removeStudentFromSubject(Long subjectId, Long studentId){
        Subject subject = entityManager.find(Subject.class, subjectId);

        subject.setStudents(null);
        entityManager.merge(subject);

    }
    // ADD
    public void TeacherToSubject(Long subjectId, Long teacherId){
        Teacher teacher = findTeacherById(teacherId);
        Subject subject = findSubjectById(subjectId);

        subject.setTeacher(teacher);
        entityManager.persist(subject);
    }

    public void StudentToSubject(Long subjectId, Long studentId) {
        Student student = findStudentById(studentId);
        Subject subject = findSubjectById(subjectId);


        subject.addStudent(student);
        entityManager.persist(subject);
    }
    // Find
    public Subject findSubjectById(Long subjectId) {
        return entityManager.find(Subject.class, subjectId);
    }

    public Teacher findTeacherById(Long teacherId) {
        return entityManager.find(Teacher.class, teacherId);
    }

    public Student findStudentById(Long studentId) {
        return entityManager.find(Student.class, studentId);
    }



}
