package io.xstefank;

import io.xstefank.model.Avenger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/avenger")
public class AvengerGeneratorResource {

    @Inject
    AvengerService avengerService;

    @GET
    @Path("/generate")
    public Avenger generateAvenger() {
        return avengerService.generateRandomAvenger();
    }
}
