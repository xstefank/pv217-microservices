package org.acme;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Path("/person")
public class PersonResource {

    @GET
    public List<Person> all() {
        return Person.listAll();
    }

    @POST
    @Transactional
    public Person add(Person person) {
        person.persist();
        return person;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Person update(Long id) {
        Person person = Person.findById(id);
        person.name = "Martin";
        return person;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Person delete(Long id) {
        Person person = Person.findById(id);
        person.delete();
        return person;
    }

    @GET
    @Path("/name/{name}")
    public Person name(String name) {
        return Person.findByName(name);
    }

    @GET
    @Path("/random")
    @Transactional
    public Person random() {
        Person person = new Person();
        person.name = UUID.randomUUID().toString();
        person.birth = LocalDate.now();
        person.status = Status.Deceased;

        person.persist();
        return person;
    }




}
