package io.xstefank;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.quarkus.panache.common.Parameters;
import io.xstefank.client.AvengerGeneratorClient;
import io.xstefank.entity.Avenger;
import io.xstefank.service.AvengerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Path("/avenger")
@ApplicationScoped
public class AvengerResource {

    @Inject
    AvengerService avengerService;

    @Inject
    @RestClient
    AvengerGeneratorClient avengerGeneratorClient;

    @POST
    @Path("/create")
    @Counted
    @Timed
    public Response createAvenger(Avenger avenger) {
        Avenger created = avengerService.createAvenger(avenger);

        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}/update")
    @Counted
    @Timed
    public Avenger updateAvenger(@PathParam("id") long id, Avenger update) {
        return avengerService.updateAvenger(id, update);
    }

    @DELETE
    @Path("/{id}/delete")
    @Counted
    @Timed
    public Response deleteAvenger(@PathParam("id") long id) {
        Avenger avenger = avengerService.deleteAvenger(id);
        return Response.ok(avenger).build();
    }

    @GET
    @Counted
    @Timed
    public List<Avenger> getAvengers() {
        return Avenger.listAll();
    }

    @GET
    @Path("/{id}")
    @Counted
    @Timed
    public Response getAvenger(@PathParam("id") long id) {
        Avenger avenger = Avenger.findById(id);

        if (avenger == null) {
            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(String.format("Avenger for id %d not found.", id))
                .build();
        }

        return Response.ok(avenger).build();
    }

    @GET
    @Path("/list")
    @Counted
    @Timed
    public List<Avenger> snappedAvenger(@QueryParam("snapped") @DefaultValue("true") boolean snapped) {
        return Avenger.list("snapped", snapped);
    }

    @GET
    @Path("/search")
    @Counted
    @Timed
    public List<Avenger> searchAvengers(@QueryParam("search") String search) {
        return Avenger.list("name like :search or civilName like :search", Parameters.with("search", "%" + search + "%"));
    }

    @GET
    @Path("/generate-team")
    @Counted
    @Timed
    public List<Avenger> generateAvengersTeam(@QueryParam("size") @DefaultValue("5") int size) {
        List<Avenger> result;
        long count = Avenger.count();
        if (size > count) {
            // we don't have enough Avengers so generate rest

            result = Avenger.listAll();

            for (int i = 0; i < size - count; i++) {
                result.add(avengerGeneratorClient.generateAvenger());
            }
        } else {
            result = new ArrayList<>();

            ThreadLocalRandom.current().longs(1, Avenger.count() + 1)
                .distinct().limit(size).forEach(i -> result.add(Avenger.findById(i)));
        }

        return result;
    }
}
