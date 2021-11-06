package io.xstefank;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SnapRepository implements PanacheMongoRepository<Snap> {

//    @Transactional
    public void init(@Observes StartupEvent event) {
        // create only if not created already (we have some data in)
        if (count() == 0) {
            Snap snapped = new Snap();
            snapped.snapped = true;
            snapped.avengers = new ArrayList<>();
            persistOrUpdate(snapped);

            Snap unsnapped = new Snap();
            unsnapped.snapped = false;
            unsnapped.avengers = new ArrayList<>();
            persistOrUpdate(unsnapped);
        }
    }

    public Snap findBySnapped(boolean snapped) {
        return find("snapped", snapped).firstResult();
    }

    public List<Snap> findSnapped(boolean snapped) {
        return list("snapped", snapped);
    }
}
