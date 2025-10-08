package io.xstefank;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Person person = new Person();
        person.name = "Loïc";
        person.birthDate = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.Alive;

        person.persist();
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/all")
    public List<Person> all() {
        return Person.listAll();
    }

    @GET
    @Path("/delete")
    public void delete() {
        Person.deleteAll();
    }




}
