package io.xstefank;

import io.xstefank.entity.Avenger;
import io.xstefank.service.AvengerService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.ClientErrorException;
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
import java.util.List;

@Path("/avenger")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvengerResource {

    @Inject
    AvengerService avengerService;

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

    @POST
    @Path("/create")
    public Avenger createAvenger(Avenger avenger) {
        return avengerService.createAvenger(avenger);
    }

    @PUT
    @Path("/{id}/update")
    public Avenger updateAvenger(@PathParam long id, JsonObject update) {
        try {
            return avengerService.updateAvenger(id, update);
        } catch (IllegalArgumentException iae) {
            throw new ClientErrorException(iae.getMessage(), Response
                .status(Response.Status.PRECONDITION_FAILED)
                .entity(iae.getMessage())
                .build());
        }
    }

    @DELETE
    @Path("/{id}/delete")
//    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAvenger(@PathParam long id) {
        Avenger avenger;

        try {
            avenger = avengerService.deleteAvenger(id);

            if (avenger == null) {
                return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Cannot delete avenger with id " + id)
                    .build();
            }
        } catch (NotFoundException nfe) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(String.format("Avenger with id %d not found.", id))
                .build();
        }

        return Response.ok(avenger).build();
    }
}

