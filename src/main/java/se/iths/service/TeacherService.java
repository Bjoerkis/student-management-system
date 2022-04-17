package se.iths.service;

import se.iths.entity.Teacher;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public Teacher findTeacherById(Long teacherId) {
        return entityManager.find(Teacher.class, teacherId);
    }

    public void updateTeacher(Teacher foundTeacher) {
        entityManager.merge(foundTeacher);
    }

    public void deleteTeacher(Long teacherId) {
        entityManager.remove(entityManager.find(Teacher.class, teacherId));
    }

    public List<Subject> findAllSubjects() {
        return entityManager.createQuery("SELECT s from Subject s", Subject.class).getResultList();
    }
}