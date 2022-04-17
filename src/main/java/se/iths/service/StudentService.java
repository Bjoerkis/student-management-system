package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void addStudent(Student student) {
        entityManager.persist(student);
    }

    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public void deleteStudent(Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
    }

    public List<Student> getStudentByLastName(String lastName) {
        String query = "SELECT s FROM Student s WHERE s.lastName LIKE '" + lastName + "'";
        return entityManager.createQuery(query, Student.class).getResultList();
    }

    public boolean emailIsNotUnique(String email) {
        String emailQuery = "SELECT s.email FROM Student s WHERE s.email LIKE '" + email + "'";
        List<String> emailResult = entityManager.createQuery(emailQuery, String.class).getResultList();

        // if email is not unique returns true
        return !emailResult.isEmpty();
    }

}
