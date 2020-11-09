package io.xstefank.service;

import io.xstefank.entity.Avenger;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class AvengerService {

    private Logger log = Logger.getLogger(AvengerService.class);

    @Transactional
    public Avenger createAvenger(Avenger avenger) {
        avenger.persist();
        return avenger;
    }

    @Transactional
    public Avenger updateAvenger(long id, JsonObject updateJson) {
        Avenger avenger = Avenger.findById(id);

        if (avenger == null) {
            return null;
        }

        updateJson.entrySet().forEach(entry -> {
            String value = entry.getValue().toString().replace("\"", "");
            switch (entry.getKey()) {
                case "id":
                    avenger.id = Long.valueOf(value);
                    break;
                case "name":
                    avenger.name = value;
                    break;
                case "civilName":
                    avenger.civilName = value;
                    break;
                case "snapped":
                    avenger.snapped = Boolean.valueOf(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown property provided for update avenger: " + entry.getKey());
            }
        });

        avenger.persist();
        return avenger;
    }

    @Transactional
    public Avenger deleteAvenger(long id) {
        Avenger avenger = Avenger.findById(id);

        if (avenger == null) {
            throw new NotFoundException("Cannot find avenger for id " + id);
        }

        boolean deleted = Avenger.deleteById(id);
        return deleted ? avenger : null;
    }
}
