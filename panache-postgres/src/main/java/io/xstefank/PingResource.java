package io.xstefank;

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

@Path("/ping")
public class PingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String testPanache() {
        // creating a person
        Person person = new Person();
        person.name = "Stef";
        person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.Alive;
        System.out.println("Created person " + person);

        // persist it
        person.persist();
        System.out.println("Persisted person " + person);

        // note that once persisted, you don't need to explicitly save your entity: all
        // modifications are automatically persisted on transaction commit.

        // getting a list of all Person entities
        List<Person> allPersons = Person.listAll();
        System.out.println("All persons " + allPersons);

        // check if it's persistent
//        if (person.isPersistent()) {
//            // delete it
//            person.delete();
//        }
//        System.out.println("Deleted person " + person);

        // finding a specific person by ID
        Person found = Person.findById(person.id);
        System.out.println("Person with id " + person.id + " is " + found);

        // finding a specific person by ID via an Optional
        Optional<Person> optional = Person.findByIdOptional(person.id);
        found = optional.orElseThrow(() -> new NotFoundException());
        System.out.println("Person with id " + person.id + " is " + found);

        // finding all living persons
        List<Person> livingPersons = Person.list("status", Status.Alive);
        System.out.println("All living persons " + livingPersons);

        // counting all persons
        long countAll = Person.count();
        System.out.println("Count all " + countAll);

        // counting all living persons
        long countAlive = Person.count("status", Status.Alive);
        System.out.println("Count alive " + countAlive);

//        // delete all living persons
//        Person.delete("status", Status.Alive);
//
//        // delete all persons
//        Person.deleteAll();
//
//        // delete by id
//        boolean deleted = Person.deleteById(person.id);

        // set the name of all living persons to 'Mortal'
        Person.update("name = 'Mortal' where status = ?1", Status.Alive);
        return "";
    }
}
