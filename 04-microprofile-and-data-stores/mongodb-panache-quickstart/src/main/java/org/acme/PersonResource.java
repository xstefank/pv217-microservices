package org.acme;

import org.acme.entity.Person;
import org.acme.entity.Status;
import org.bson.types.ObjectId;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Path("/persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public List<Person> list() {
        return Person.listAll();
    }

    @GET
    @Path("/{id}")
    public Person get(String id) {
        return Person.findById(new ObjectId(id));
    }

    @POST
    public Response create(Person person) {
        person.persist();
        return Response.created(URI.create("/persons/" + person.id)).build();
    }

    @PUT
    @Path("/{id}")
    public void update(String id, Person person) {
        person.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(String id) {
        Person person = Person.findById(new ObjectId(id));
        if(person == null) {
            throw new NotFoundException();
        }
        person.delete();
    }

    @GET
    @Path("/search/{name}")
    public Person search(String name) {
        return Person.findByName(name);
    }

    @GET
    @Path("/count")
    public Long count() {
        return Person.count();
    }

    @GET
    @Path("/basics")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String basics() {
        // creating a person
        Person person = new Person();
        person.name = "Loïc";
        person.birthDate = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.Alive;

// persist it: if you keep the default ObjectId ID field, it will be populated by the MongoDB driver
        person.persist();

//        person.status = Status.Dead;

// Your must call update() in order to send your entity modifications to MongoDB
        person.update();

// delete it
//        person.delete();

// getting a list of all Person entities
        List<Person> allPersons = Person.listAll();
        System.out.println(allPersons);

// finding a specific person by ID
// here we build a new ObjectId, but you can also retrieve it from the existing entity after being persisted
        ObjectId personId = new ObjectId(person.id.toHexString());
        person = Person.findById(personId);
        System.out.println(person);

// finding a specific person by ID via an Optional
        Optional<Person> optional = Person.findByIdOptional(personId);
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
//        boolean deleted = Person.deleteById(personId);

// set the name of all living persons to 'Mortal'
//        long updated = Person.update("name", "Mortal").where("status", Status.Alive);

        return "Hello from Mongo Panache";
    }


}
