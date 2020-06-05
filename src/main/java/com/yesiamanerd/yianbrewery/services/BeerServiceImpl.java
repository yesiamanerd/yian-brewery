package com.yesiamanerd.yianbrewery.services;

import com.yesiamanerd.yianbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Cosmic Cat")
                .beerStyle("Pale Ale")
                .build();
    }
}
