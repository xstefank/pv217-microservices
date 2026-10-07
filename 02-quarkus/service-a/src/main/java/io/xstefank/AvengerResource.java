package io.xstefank;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.Collection;

@Path("/avenger")
public class AvengerResource {

    @Inject
    AvengerRepository avengerRepository;

    @GET
    public Collection<Avenger> getAll() {
        return avengerRepository.getAvengers();
    }

    @POST
    public Avenger createAvenger(Avenger avenger) {
        return avengerRepository.addAvenger(avenger);
    }
}
