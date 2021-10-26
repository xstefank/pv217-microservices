package io.xstefank;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.xstefank.model.Person;
import io.xstefank.model.Status;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;

@Path("/ping")
public class PingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String hello() {

        Person person = new Person();
        person.name = "Test person";
        person.birth = LocalDate.now();
        person.status = Status.Alive;

        person.persist();

        Person person2 = new Person();
        person2.name = "Test person 2";
        person2.birth = LocalDate.now();
        person2.status = Status.Dead;

        person.persist();
        person2.persist();

        return "Hello RESTEasy";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    @Path("/panache")
    public String hello2() {

        List<PanacheEntityBase> list = Person.listAll();
        System.out.println(list);

        System.out.println(Person.list("status", Status.Alive));

        return "Hello RESTEasy";
    }
}
