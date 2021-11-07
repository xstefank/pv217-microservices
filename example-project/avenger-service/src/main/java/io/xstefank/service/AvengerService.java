package io.xstefank.service;

import io.xstefank.client.SnapServiceClient;
import io.xstefank.entity.Avenger;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class AvengerService {

    private static final Logger log = Logger.getLogger(AvengerService.class);

    @Inject
    @RestClient
    SnapServiceClient snapServiceClient;

    @Transactional
    public Avenger createAvenger(Avenger avenger) {
        boolean snapped = getSnap(avenger);
        avenger.snapped = snapped;

        avenger.persist();
        return avenger;
    }

    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "defaultSnap")
    public boolean getSnap(Avenger avenger) {
        return snapServiceClient.shouldBeSnapped(avenger);
    }

    private boolean defaultSnap(Avenger avenger) {
        // rather snap then don't snap
        return true;
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
