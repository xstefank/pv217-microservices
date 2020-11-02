package org.acme.mongodb.panache;

import org.acme.mongodb.panache.entity.Person;
import org.acme.mongodb.panache.entity.Status;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.Month;

@Path("/person")
public class PersonResource {

    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Person person = new Person();
        person.name = "Loïc";
        person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.Alive;

//        person.persist();

        return person.name;
    }
}
