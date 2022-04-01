package se.iths.service;


import se.iths.entity.Student;
import se.iths.exceptions.EmailException;

import javax.persistence.Entity;
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
        String query = "SELECT s FROM Student s WHERE s.lastName = '" + lastName + "'";
        return entityManager.createQuery(query, Student.class).getResultList();
    }

    public boolean EmailIsNotUnique(String email) {
        List<String> emailAdresses = entityManager.createQuery("SELECT s.email FROM Student s", String.class).getResultList();
        for (String emailInDatabase : emailAdresses) {
            if (emailInDatabase.equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

}
