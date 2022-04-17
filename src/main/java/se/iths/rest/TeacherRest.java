package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.exceptions.ResponseAsJson;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {

        try {
            teacherService.createTeacher(teacher);
        } catch (ValidationException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResponseAsJson(400, "Bad Request",
                            "Name required")).build());
        }
        return Response.ok().entity(new ResponseAsJson(200, "OK",
                "Teacher created successfully")).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateTeacher(@PathParam("id") Long teacherId, @QueryParam("newname") String newName) {

        Teacher teacher = teacherService.findTeacherById(teacherId);

        if (teacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A teacher with id: " + teacherId + "was not found.")).build());
        }

        try {
            teacher.setName(newName);
        } catch (ValidationException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResponseAsJson(400, "Bad Request",
                            "Name required")).build());
        }
        teacherService.updateTeacher(teacher);

        return Response.ok().entity(new ResponseAsJson(200, "OK",
                "Teacher updated successfully")).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long teacherId) {

        Teacher foundTeacher = teacherService.findTeacherById(teacherId);
        if (foundTeacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseAsJson(404, "Not Found",
                            "A teacher with id: " + teacherId + "was not found.")).build());
        }


        for (Subject subject : teacherService.findAllSubjects()) {

            if (subject.getTeacher() != null) {
                if (Objects.equals(subject.getTeacher().getId(), teacherId)) {
                    throw new WebApplicationException(Response.status(Response.Status.CONFLICT)
                            .entity(new ResponseAsJson(409, "Conflict",
                                    "You can't delete teachers who are assigned to subjects.")).build());
                }
            }
        }

        teacherService.deleteTeacher(teacherId);
        return Response.ok().entity(new ResponseAsJson(200, "OK",
                "Teacher with id: " + teacherId + " deleted from database.")).build();
    }
}
