package io.xstefank;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/avenger")
public class AvengerResource {

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Avenger create(Avenger avenger) {
        avenger.persist();
        return avenger;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public List<Avenger> getAvengers() {
//        System.out.println(Avenger.list("snapped", false));
//        System.out.println(Avenger.list("name like :search", Parameters.with("search", "%" + "a" + "%")));

        return Avenger.listAll();
    }

    @GET
    @Path("/ping")
    public String hello() {
        return "hello";
    }

    @GET
    @Path("/call-another")
    public String callAnother() {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8081")
            .path("/ping")
            .request()
            .get();

        System.out.println(response.getStatus());

        Avenger s = response.readEntity(Avenger.class);
        System.out.println(s);
        return s.toString();
    }

    @Inject
    FailingService failingService;

    @GET
    @Path("/failing")
    public String failing() {
        return failingService.getHello();
    }

}
