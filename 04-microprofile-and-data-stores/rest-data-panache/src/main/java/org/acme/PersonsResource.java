package org.acme;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import org.acme.entity.Person;

public interface PersonsResource extends PanacheEntityResource<Person, Long> {
}
