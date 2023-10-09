package io.xstefank;

import io.xstefank.model.Avenger;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

import java.util.List;
import java.util.UUID;

@Path("/avenger")
public class AvengerResource {

    @GET
    public List<Avenger> all() {
        return Avenger.listAll();
    }


    @POST
    @Transactional
    public Avenger add(Avenger avenger) {
        System.out.println("avenger = " + avenger);
        avenger.persist();
        System.out.println("avenger = " + avenger);
        return avenger;
    }

    @GET
    @Path("/random")
    @Transactional
    public Avenger random() {
        Avenger avenger = new Avenger();
        avenger.name = UUID.randomUUID().toString();
        avenger.civilName = "civil " + UUID.randomUUID().toString();
        avenger.snapped = false;

        avenger.persist();
        return avenger;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Avenger update(Long id) {
        Avenger avenger = Avenger.findById(id);
        if (avenger == null) {
            throw new NotFoundException();
        }

        avenger.civilName = "UPDATED";

        avenger.snapped = false;
        return avenger;
    }

    @DELETE
    @Transactional
    @Path("/{number}")
    public Avenger delete(Long number) {
        Avenger avenger = Avenger.findById(number);
        avenger.delete();
        return avenger;
    }

//    @PUT
//    @Path("/{id}")
//    @Transactional
//    public void update(Long id, @QueryParam("testQuery") String query,
//                       @HeaderParam("header") String header) {
//        System.out.println("id = " + id);
//        System.out.println("query = " + query);
//        System.out.println("header = " + header);
//
//    }




}
