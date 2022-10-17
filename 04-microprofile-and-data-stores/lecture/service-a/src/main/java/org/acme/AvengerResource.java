package org.acme;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import org.acme.entity.Avenger;

public interface AvengerResource extends PanacheEntityResource<Avenger, Long> {
}
