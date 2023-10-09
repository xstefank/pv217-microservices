package io.xstefank;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.xstefank.model.Avenger;

@ResourceProperties(path = "/generated")
public interface AvengersResource
    extends PanacheEntityResource<Avenger, Long> {
}
