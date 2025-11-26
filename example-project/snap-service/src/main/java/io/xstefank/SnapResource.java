package io.xstefank;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.xstefank.data.Snap;
import io.xstefank.data.SnapRepository;
import io.xstefank.json.Avenger;
import io.xstefank.service.SnapGenerator;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/snap")
public class SnapResource {

    @Inject
    SnapGenerator snapGenerator;

    @Inject
    SnapRepository snapRepository;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("user")
    @Counted("snap.create.counter")
    @Timed("snap.create.timer")
    public boolean createSnap(Avenger avenger) {
        boolean snapped = snapGenerator.shouldBeSnap(avenger.name);

        // persist to my NoSQL DB
        Snap snap = snapRepository.findBySnapped(snapped);
        snap.avengers.add(avenger.name);
        snap.persistOrUpdate();

        return snapped;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Counted("snap.list.counter")
    @Timed("snap.list.timer")
    public List<Snap> getSnaps(@QueryParam("snapped") @DefaultValue("true") boolean snapped) {
        return snapRepository.findSnapped(snapped);
    }
}