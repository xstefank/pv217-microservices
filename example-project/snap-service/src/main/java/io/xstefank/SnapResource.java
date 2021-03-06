package io.xstefank;

import io.xstefank.json.Avenger;
import io.xstefank.service.SnapGenerator;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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
    @Counted(name = "snap.create.counter")
    @Timed(name = "snap.create.timer")
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
    @Counted(name = "snap.list.counter")
    @Timed(name = "snap.list.timer")
    public List<Snap> getSnaps(@QueryParam("snapped") @DefaultValue("true") boolean snapped) {
        return snapRepository.findSnapped(snapped);
    }
}
