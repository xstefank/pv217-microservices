package io.xstefank;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.panache.common.Parameters;
import io.xstefank.client.AvengerGeneratorClient;
import io.xstefank.entity.Avenger;
import io.xstefank.service.AvengerService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
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

    @Inject
    MeterRegistry meterRegistry;

    @POST
    @Path("/create")
    @Counted("avenger.create.counter")
    @Timed("avenger.create.timer")
    public Response createAvenger(Avenger avenger) {
        Avenger created = avengerService.createAvenger(avenger);

        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}/update")
    @Counted("avenger.update.counter")
    @Timed("avenger.update.timer")
    public Avenger updateAvenger(@PathParam long id, Avenger update) {
        return avengerService.updateAvenger(id, update);
    }

    @DELETE
    @Path("/{id}/delete")
    @Counted("avenger.delete.counter")
    @Timed("avenger.delete.timer")
    public Response deleteAvenger(@PathParam long id) {
        Avenger avenger = avengerService.deleteAvenger(id);
        return Response.ok(avenger).build();
    }

    @GET
    @Counted("avenger.getAll.counter")
    @Timed("avenger.getAll.timer")
    public List<Avenger> getAvengers() {
        return Avenger.listAll();
    }

    @GET
    @Path("/{id}")
    @Counted("avenger.getOne.counter")
    @Timed("avenger.getOne.timer")
    public Response getAvenger(@PathParam long id) {
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
    @Counted("avenger.list.counter")
    @Timed("avenger.list.timer")
    public List<Avenger> snappedAvenger(@QueryParam("snapped") @DefaultValue("true") boolean snapped) {
        return Avenger.list("snapped", snapped);
    }

    @GET
    @Path("/search")
    @Counted("avenger.search.counter")
    @Timed("avenger.search.timer")
    public List<Avenger> searchAvengers(@QueryParam("search") String search) {
        return Avenger.list("name like :search or civilName like :search", Parameters.with("search", "%" + search + "%"));
    }

    @GET
    @Path("/generate-team")
    @Counted("avenger.team.generate.counter")
    @Timed("avenger.team.generate.timer")
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