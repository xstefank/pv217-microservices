package io.xstefank;

import io.xstefank.model.Avenger;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/avenger")
public class AvengerGeneratorResource {

    @Inject
    AvengerService avengerService;

    @GET
    @Path("/generate")
    @RolesAllowed("user")
    @Counted(name = "avenger.generated.counter")
    @Timed(name = "avenger.generated.timer")
    public Avenger generateAvenger() {
        return avengerService.generateRandomAvenger();
    }
}
