package io.xstefank;

import com.fasterxml.jackson.databind.JsonNode;
import io.quarkus.panache.common.Parameters;
import io.xstefank.entity.Avenger;
import io.xstefank.service.AvengerService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
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
    public Avenger createAvenger(Avenger avenger) {
        return avengerService.createAvenger(avenger);
    }

    @PUT
    @Path("/{id}/update")
    public Avenger updateAvenger(@PathParam long id, Avenger update) {
        return avengerService.updateAvenger(id, update);
    }

    @DELETE
    @Path("/{id}/delete")
    @RolesAllowed("Admin")
    public Response deleteAvenger(@PathParam long id) {
        Avenger avenger = avengerService.deleteAvenger(id);
        return Response.ok(avenger).build();
    }

    @GET
    public List<Avenger> getAvengers() {
        return Avenger.listAll();
    }

    @GET
    @Path("/{id}")
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
    public List<Avenger> snappedAvenger(@QueryParam("snapped") @DefaultValue("true") boolean snapped) {
        return Avenger.list("snapped", snapped);
    }

    @GET
    @Path("/search")
    public List<Avenger> searchAvengers(@QueryParam("search") String search) {
        return Avenger.list("name like :search or civilName like :search", Parameters.with("search", "%" + search + "%"));
    }
}

