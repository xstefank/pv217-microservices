package io.xstefank;

import javax.inject.Inject;
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

    @Inject
    PersonRepository personRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String testPersonRepository() {
        // creating a person
        Person person = new Person();
        person.setName("Stef");
        person.setBirth(LocalDate.of(1910, Month.FEBRUARY, 1));
        person.setStatus(Status.Alive);

        // persist it
        personRepository.persist(person);
        System.out.println("Persisted person " + person);

        // note that once persisted, you don't need to explicitly save your entity: all
        // modifications are automatically persisted on transaction commit.

//        // check if it's persistent
//        if(personRepository.isPersistent(person)){
//            // delete it
//            personRepository.delete(person);
//        }
//        System.out.println("Deleted person " + person);

        // getting a list of all Person entities
        List<Person> allPersons = personRepository.listAll();
        System.out.println("All persons " + allPersons);


        // finding a specific person by ID
        Person found = personRepository.findById(person.getId());
        System.out.println("The person with id " + person.getId() + " is " + found);

        // finding a specific person by ID via an Optional
        Optional<Person> optional = personRepository.findByIdOptional(person.getId());
        found = optional.orElseThrow(() -> new NotFoundException());
        System.out.println("The person with id " + person.getId() + " is " + found);

        // finding all living persons
        List<Person> livingPersons = personRepository.list("status", Status.Alive);
        System.out.println("All living persons " + livingPersons);

        // counting all persons
        long countAll = personRepository.count();
        System.out.println("Cound all " + countAll);

        // counting all living persons
        long countAlive = personRepository.count("status", Status.Alive);
        System.out.println("Count alive " + countAlive);

//        // delete all living persons
//        personRepository.delete("status", Status.Alive);
//
//        // delete all persons
//        personRepository.deleteAll();
//
//        // delete by id
//        boolean deleted = personRepository.deleteById(personId);

        // set the name of all living persons to 'Mortal'
        personRepository.update("name = 'Mortal' where status = ?1", Status.Alive);

        return "";
    }
}
