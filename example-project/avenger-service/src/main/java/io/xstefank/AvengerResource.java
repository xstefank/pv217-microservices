package io.xstefank;

import io.quarkus.panache.common.Parameters;
import io.xstefank.entity.Avenger;
import io.xstefank.service.AvengerService;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
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
import java.util.List;

@Path("/avenger")
@ApplicationScoped
public class AvengerResource {

    @Inject
    AvengerService avengerService;

    @POST
    @Path("/create")
    @Counted(name = "avenger.create.counter")
    @Timed(name = "avenger.create.timer")
    public Response createAvenger(Avenger avenger) {
        Avenger created = avengerService.createAvenger(avenger);

        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}/update")
    @Counted(name = "avenger.update.counter")
    @Timed(name = "avenger.update.timer")
    public Avenger updateAvenger(@PathParam long id, Avenger update) {
        return avengerService.updateAvenger(id, update);
    }

    @DELETE
    @Path("/{id}/delete")
    @RolesAllowed("Admin")
    @Counted(name = "avenger.delete.counter")
    @Timed(name = "avenger.delete.timer")
    public Response deleteAvenger(@PathParam long id) {
        Avenger avenger = avengerService.deleteAvenger(id);
        return Response.ok(avenger).build();
    }

    @GET
    @Counted(name = "avenger.getAll.counter")
    @Timed(name = "avenger.getAll.timer")
    public List<Avenger> getAvengers() {
        return Avenger.listAll();
    }

    @GET
    @Path("/{id}")
    @Counted(name = "avenger.getOne.counter")
    @Timed(name = "avenger.getOne.timer")
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
    @Counted(name = "avenger.list.counter")
    @Timed(name = "avenger.list.timer")
    public List<Avenger> snappedAvenger(@QueryParam("snapped") @DefaultValue("true") boolean snapped) {
        return Avenger.list("snapped", snapped);
    }

    @GET
    @Path("/search")
    @Counted(name = "avenger.search.counter")
    @Timed(name = "avenger.search.timer")
    public List<Avenger> searchAvengers(@QueryParam("search") String search) {
        return Avenger.list("name like :search or civilName like :search", Parameters.with("search", "%" + search + "%"));
    }
}

