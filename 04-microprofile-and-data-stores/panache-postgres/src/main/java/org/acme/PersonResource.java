package org.acme;

import org.acme.entity.Person;
import org.acme.entity.Status;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Path("/person")
public class PersonResource {

    @GET
    @Path("/basics")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String basicOperations() {
        // creating a person
        Person person = new Person();
        person.name = "Stef";
        person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.Alive;

// persist it
        person.persist();

// note that once persisted, you don't need to explicitly save your entity: all
// modifications are automatically persisted on transaction commit.

// check if it is persistent
//        if (person.isPersistent()) {
//            // delete it
//            person.delete();
//        }

// getting a list of all Person entities
        List<Person> allPersons = Person.listAll();
        System.out.println(allPersons);

// finding a specific person by ID
        person = Person.findById(person.id);
        System.out.println(person);

// finding a specific person by ID via an Optional
        Optional<Person> optional = Person.findByIdOptional(person.id);
        person = optional.orElseThrow(() -> new NotFoundException());
        System.out.println(person);

// finding all living persons
        List<Person> livingPersons = Person.list("status", Status.Alive);
        System.out.println(livingPersons);

// counting all persons
        long countAll = Person.count();
        System.out.println(countAll);

// counting all living persons
        long countAlive = Person.count("status", Status.Alive);
        System.out.println(countAlive);

// delete all living persons
//        Person.delete("status", Status.Alive);

// delete all persons
//        Person.deleteAll();

// delete by id
//        boolean deleted = Person.deleteById(person.id);

// set the name of all living persons to 'Mortal'
//        Person.update("name = 'Mortal' where status = ?1", Status.Alive);
        return "Hello from Panache";
    }
}