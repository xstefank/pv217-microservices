package io.xstefank;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.xstefank.entity.Person;
import io.xstefank.entity.Status;
import io.xstefank.repository.PersonRepository;

import javax.inject.Inject;
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

    @Inject
    PersonRepository personRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons() {
//        System.out.println(personRepository.findById(1L));
//        System.out.println(personRepository.list("status", Status.Alive));
//        System.out.println(personRepository.list("name like :name", Parameters.with("name", "%an%")));

//        System.out.println(personRepository.list("order by birth"));
//        System.out.println(personRepository.listAll(Sort.by("birth")));
//        System.out.println(personRepository.list("status", Sort.by("birth"), Status.Alive));

//        List<String> collect = personRepository.streamAll()
//            .map(person -> person.getName().toUpperCase())
//            .limit(2)
//            .collect(Collectors.toList());
//
//        System.out.println(collect);

//        System.out.println(personRepository.count());
//        System.out.println(personRepository.count("status", Status.Alive));

        return personRepository.listAll();
    }

    @GET
    @Path("/entity-methods")
    public void entityMethods() {
        System.out.println(personRepository.findAlive());
        System.out.println(personRepository.findByName("Janko"));
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Person createPerson(Person person) {
        personRepository.persist(person);

        return person;
    }

    @DELETE
    @Path("delete-dead")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteDead() {
        personRepository.delete("status", Status.Dead);
    }

    @PUT
    @Path("update-mortals")
    @Transactional
    public void updateMortals() {
        personRepository.update("name = 'Mortal' where status = ?1", Status.Alive);
    }

}
