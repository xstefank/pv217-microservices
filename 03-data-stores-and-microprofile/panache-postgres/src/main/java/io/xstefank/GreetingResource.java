package io.xstefank;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.Person;
import org.acme.Status;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String hello() {
        Person person = new Person();
        person.name = "Stef";
        person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.Alive;

        person.persist();
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/all")
    public List<Person> all() {
        return Person.listAll();
    }


}
