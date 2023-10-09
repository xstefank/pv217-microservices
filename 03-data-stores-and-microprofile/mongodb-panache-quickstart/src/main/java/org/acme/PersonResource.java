package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/person")
public class PersonResource {

    @GET
    public List<Person> all() {
        return Person.listAll();
    }

    @POST
    public Person add(Person person) {
        person.persist();
        return person;
    }

}
