package io.xstefank;

import io.xstefank.model.Avenger;

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
    public Avenger generateAvenger() {
        return avengerService.generateRandomAvenger();
    }
}
