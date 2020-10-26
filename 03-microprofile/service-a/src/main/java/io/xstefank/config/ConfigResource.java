package io.xstefank.config;

import io.xstefank.Course;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/config")
public class ConfigResource {

    @ConfigProperty(name = "greeting.message")
    Optional<String> message;

    @Inject
    CourseConfiguration courseConfiguration;

    @ConfigProperty(name = "course.custom")
    Course customCourse;

    @GET
    @Path("/converter")
    public Response getWithConverter() {
        System.out.println(customCourse);
        return Response.ok().build();
    }


    @GET
    @Path("/course")
    public Response getCourse() {
        System.out.println(courseConfiguration.name);
        System.out.println(courseConfiguration.studentCount);
        return Response.ok().build();
    }

    @GET
    @Path("/message")
    public Response getMessage() {
        return Response.ok(message.orElse("no value provided")).build();
    }
}
