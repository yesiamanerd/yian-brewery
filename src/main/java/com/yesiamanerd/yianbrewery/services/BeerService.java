package com.yesiamanerd.yianbrewery.services;

import com.yesiamanerd.yianbrewery.web.model.BeerDto;

import java.util.UUID;

@Deprecated
public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);

    void deleteById(UUID beerId);
}
