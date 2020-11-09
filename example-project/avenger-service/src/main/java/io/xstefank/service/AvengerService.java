package io.xstefank.service;

import io.xstefank.entity.Avenger;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class AvengerService {

    @Transactional
    public Avenger createAvenger(Avenger avenger) {
        avenger.persist();
        return avenger;
    }

//    @Transactional
//    public Avenger updateAvenger(Avenger avenger) {
//
//    }
}
