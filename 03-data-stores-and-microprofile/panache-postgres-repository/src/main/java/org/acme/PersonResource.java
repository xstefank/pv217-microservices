package org.acme;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/person")
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @GET
    public List<Person> all() {
        return personRepository.listAll();
    }

    @GET
    @Path("/count")
    public long count(){
        return personRepository.count();
    }

    @POST
    @Transactional
    public Person add(Person person) {
        personRepository.persist(person);
        return person;
    }
}
