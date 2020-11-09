package io.xstefank;

import io.xstefank.json.Avenger;
import io.xstefank.service.SnapService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/snap")
public class SnapResource {

    @Inject
    SnapService snapService;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean createSnap(Avenger avenger) {
        return snapService.shouldBeSnap(avenger.name);
    }
}
