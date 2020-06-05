package com.yesiamanerd.yianbrewery.services.v2;

import com.yesiamanerd.yianbrewery.web.model.BeerDto;
import com.yesiamanerd.yianbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDtoV2 getBeerById(UUID beerId);

    BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto);

    void updateBeer(UUID beerId, BeerDtoV2 beerDto);

    void deleteById(UUID beerId);
}
