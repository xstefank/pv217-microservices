package io.xstefank.service;

import io.xstefank.entity.Avenger;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class AvengerService {

    private Logger log = Logger.getLogger(AvengerService.class);

    @Transactional
    public Avenger createAvenger(Avenger avenger) {
        boolean snapped = false; // TODO call snap service here
        avenger.snapped = snapped;

        avenger.persist();
        return avenger;
    }

    @Transactional
    public Avenger updateAvenger(long id, Avenger update) {
        Avenger avenger = Avenger.findById(id);

        if (avenger == null) {
            throw new NotFoundException(String.format("Avenger with id %d not found", id));
        }

        avenger.merge(update);

        avenger.persist();
        return avenger;
    }

    @Transactional
    public Avenger deleteAvenger(long id) {
        Avenger avenger = Avenger.findById(id);

        if (avenger == null) {
            throw new NotFoundException(String.format("Avenger with id %d not found", id));
        }

        boolean deleted = Avenger.deleteById(id);
        return deleted ? avenger : null;
    }
}
