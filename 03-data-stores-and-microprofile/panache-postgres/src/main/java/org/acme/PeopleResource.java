package org.acme;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface PeopleResource extends PanacheEntityResource<Person, Long> {
}
