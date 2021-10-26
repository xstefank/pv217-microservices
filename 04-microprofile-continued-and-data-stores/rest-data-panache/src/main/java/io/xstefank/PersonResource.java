package io.xstefank;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.xstefank.entity.Person;

public interface PersonResource extends PanacheEntityResource<Person, Long> {
}
