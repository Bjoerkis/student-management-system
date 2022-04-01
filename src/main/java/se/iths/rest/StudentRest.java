package se.iths.rest;

import se.iths.entity.Student;


import se.iths.service.StudentService;

import javax.inject.Inject;


import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {
    StudentService studentService;

    @Inject
    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @Path("new")
    @POST
    public Response addStudent(Student student) {
        if (studentService.emailIsNotUnique(student.getEmail())) {
            return Response.status(Response.Status.CONFLICT).entity("E-mail is already in the database.").type(MediaType.TEXT_PLAIN_TYPE).build();
        }
        try {
            studentService.addStudent(student);
        } catch (ValidationException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Please enter your first And last name as well as your e-mail!" + " " + "Exception: " + e).type(MediaType.TEXT_PLAIN_TYPE).build());
        }

        return Response.ok().entity("Student created successfully").type(MediaType.TEXT_PLAIN_TYPE).build();
    }

    @Path("update/{id}")
    @PATCH
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        Student foundStudent = studentService.findStudentById(id);
        if (foundStudent == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("There are no students with that ID currently stored in the database.").type(MediaType.TEXT_PLAIN_TYPE).build();
        }

        if (student.getFirstName() != null) {
            foundStudent.setFirstName(student.getFirstName());
        }
        if (student.getLastName() != null) {
            foundStudent.setLastName(student.getLastName());
        }
        if (student.getEmail() != null) {
            foundStudent.setEmail(student.getEmail());
        }
        if (student.getPhoneNumber() != null) {
            foundStudent.setPhoneNumber(student.getPhoneNumber());
        }

        studentService.updateStudent(foundStudent);

        return Response.ok(student).build();
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id) {
        Student foundStudent = studentService.findStudentById(id);
        if (foundStudent == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(foundStudent).build();
    }

    @Path("studentbylastname")
    @GET
    public List<Student> getStudentByLastName(@QueryParam("lastName") String lastName) {
        List<Student> foundStudents = studentService.getStudentByLastName(lastName);
        if (foundStudents.size() == 0) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity("There were no students with that last name currently in the database!").type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return studentService.getStudentByLastName(lastName);
    }

    @Path("getall")
    @GET
    public Response getAllStudents() {
        List<Student> foundStudents = studentService.getAllStudents();
        if (foundStudents.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else
            return Response.ok(foundStudents).build();

    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        Student foundStudent = studentService.findStudentById(id);
        if (foundStudent == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("There are no students with that ID currently stored in the database").type(MediaType.TEXT_PLAIN_TYPE).build();
        }
        studentService.deleteStudent(id);

        String success = "Success";
        return Response.ok(success).entity("Student deleted from database.").type(MediaType.TEXT_PLAIN_TYPE).build();
    }


}
