package org.acme.mongodb.panache;

import org.bson.types.ObjectId;

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
    public String testPanache() {
        // creating a person
        Person person = new Person();
        person.name = "Loïc";
        person.birthDate = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.Alive;

        // persist it: if you keep the default ObjectId ID field, it will be populated by the MongoDB driver
        person.persist();
        System.out.println("Persisted person " + person);

        person.status = Status.Dead;

        // Your must call update() in order to send your entity modifications to MongoDB
        person.update();
        System.out.println("Updated person " + person);

//        // delete it
//        person.delete();

        // getting a list of all Person entities
        List<Person> allPersons = Person.listAll();
        System.out.println("All persons " + allPersons);

        // finding a specific person by ID
        // here we build a new ObjectId but you can also retrieve it from the existing entity after being persisted
        ObjectId personId = new ObjectId(person.id.toHexString());
        Person found = Person.findById(personId);
        System.out.println("Found person for id " + personId + " - " + found);

        // finding a specific person by ID via an Optional
        Optional<Person> optional = Person.findByIdOptional(personId);
        found = optional.orElseThrow(() -> new NotFoundException());
        System.out.println("Found person for id " + personId + " - " + found);

        // finding all living persons
        List<Person> livingPersons = Person.list("status", Status.Alive);
        System.out.println("Living persons " + livingPersons);

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
//        boolean deleted = Person.deleteById(personId);

        // set the name of all living persons to 'Mortal'
        long updated = Person.update("name", "Mortal").where("status", Status.Alive);

        return "";
    }
}
