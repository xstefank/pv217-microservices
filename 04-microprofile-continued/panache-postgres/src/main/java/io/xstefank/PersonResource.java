package io.xstefank;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.xstefank.entity.Person;
import io.xstefank.entity.Status;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/person")
public class PersonResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons() {
//        System.out.println(Person.findById(1L));
//        System.out.println(Person.list("status", Status.Alive));
//        System.out.println(Person.list("name like :name", Parameters.with("name", "%an%")));

//        System.out.println(Person.list("order by birth"));
//        System.out.println(Person.listAll(Sort.by("birth")));
//        System.out.println(Person.list("status", Sort.by("birth"), Status.Alive));

//        List<String> collect = Person.<Person>streamAll()
//            .map(person -> person.name = person.name.toUpperCase())
//            .limit(2)
//            .collect(Collectors.toList());
//
//        System.out.println(collect);

//        System.out.println(Person.count());
//        System.out.println(Person.count("status", Status.Alive));

        return Person.listAll();
    }

    @GET
    @Path("/entity-methods")
    public void entityMethods() {
        System.out.println(Person.findAlive());
        System.out.println(Person.findByName("Janko"));
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Person createPerson(Person person) {
        person.persist();

        return person;
    }

    @DELETE
    @Path("delete-dead")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteDead() {
        Person.delete("status", Status.Dead);
    }

    @PUT
    @Path("update-mortals")
    @Transactional
    public void updateMortals() {
        Person.update("name = 'Mortal' where status = ?1", Status.Alive);
    }
}
