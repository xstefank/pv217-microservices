package io.xstefank;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.xstefank.model.Avenger;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/avenger")
public class AvengerGeneratorResource {

    @Inject
    AvengerService avengerService;

    @GET
    @Path("/generate")
    @RolesAllowed("user")
    @Counted("avenger.generated.counter")
    @Timed("avenger.generated.timer")
    public Avenger generateAvenger() {
        return avengerService.generateRandomAvenger();
    }
}