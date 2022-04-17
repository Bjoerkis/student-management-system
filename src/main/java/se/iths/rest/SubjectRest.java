package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.exceptions.ResponseAsJson;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("")
    @POST
    public Response createSubject(Subject subject) {
        try {
            subjectService.createSubject(subject);
        } catch (ValidationException ve) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResponseAsJson(400, "Bad Request",
                            "Subject name required")).build());
        }
        return Response.ok().entity(new ResponseAsJson(200, "OK",
                "Subject created")).build();
    }

    @Path("addstudent")
    @GET
    public Response StudentToSubject(@QueryParam("subjectid") Long subjectId, @QueryParam("studentid") Long studentId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);
        Student foundStudent = subjectService.findStudentById(studentId);

        if (foundSubject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A subject with id " + subjectId + " was not found.")).build());
        }
        if (foundStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A student with id: " + studentId + " was not found.")).build());
        }

        for (Student student : foundSubject.getStudents()) {
            if (Objects.equals(student.getId(), studentId)) {
                throw new WebApplicationException(Response.status(Response.Status.CONFLICT)
                        .entity(new ResponseAsJson(409, "Conflict",
                                "Id: " + studentId + " has already assigned to that subject.")).build());
            }
        }
        subjectService.StudentToSubject(subjectId, studentId);
        return Response.ok().entity(new ResponseAsJson(200, "OK",
                "Student successfully added to subject")).build();
    }

    @Path("addteacher")
    @GET
    public Response TeacherToSubject(@QueryParam("subjectid") Long subjectId, @QueryParam("teacherid") Long teacherId) {
        Subject subject = subjectService.findSubjectById(subjectId);
        Teacher teacher = subjectService.findTeacherById(teacherId);

        if (subject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A subject with id: " + subjectId + "was not found.")).build());
        }

        if (teacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A teacher with id: " + teacherId + "was not found.")).build());
        }

        subjectService.TeacherToSubject(subjectId, teacherId);
        return Response.ok().entity(new ResponseAsJson(200, "OK",
                "Teacher added to subject.")).build();
    }

    @Path("search")
    @GET
    public Response getStudentsAndTeacher(@QueryParam("subjectid") Long subjectId) {
        Subject subject = subjectService.findSubjectById(subjectId);
        if (subject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A subject with id: " + subjectId + "was not found.")).build());
        }

        return Response.ok(subject).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long subjectId) {
        if (subjectService.findSubjectById(subjectId) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A subject with id: " + subjectId + "was not found.")).build());
        }

        subjectService.removeSubject(subjectId);
        return Response.ok(new ResponseAsJson(200, "OK",
                "Id: " + subjectId + " successfully deleted.")).build();
    }

    @Path("deleteteacher")
    @DELETE
    public Response removeTeacherFromSubject(@QueryParam("subjectid") Long subjectId, @QueryParam("teacherid") Long teacherId) {
        Subject subject = subjectService.findSubjectById(subjectId);
        Teacher teacher = subjectService.findTeacherById(teacherId);

        if (subject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A subject with id: " + subjectId + "was not found.")).build());
        }

        if (teacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A teacher with id: " + teacherId + "was not found.")).build());
        }

        if (subject.getTeacher() != null) {
            if (Objects.equals(subject.getTeacher().getId(), teacherId)) {
                subjectService.removeTeacherFromSubject(subjectId, teacherId);
                return Response.ok().entity(new ResponseAsJson(200, "OK",
                        "Removal successful.")).build();
            }
        }

        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                .entity(new ResponseAsJson(404, "Not Found",
                        "id: " + teacherId + " is not assigned to that subject.")).build());
    }

    @Path("deletestudent")
    @DELETE
    public Response removeStudentFromSubject(@QueryParam("subjectid") Long subjectId, @QueryParam("studentid") Long studentId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);
        Student foundStudent = subjectService.findStudentById(studentId);

        if (foundSubject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A subject with id: " + subjectId + "was not found.")).build());
        }

        if (foundStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A student with id: " + studentId + "was not found.")).build());
        }

        for (Student student : foundSubject.getStudents()) {
            if (Objects.equals(student.getId(), studentId)) {
                subjectService.removeStudentFromSubject(subjectId, studentId);
                return Response.ok().entity(new ResponseAsJson(200, "OK",
                        "Removal successful.")).build();
            }
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                .entity(new ResponseAsJson(404, "Not Found",
                        "Id: " + studentId + " is not assigned to that subject.")).build());
    }
}
