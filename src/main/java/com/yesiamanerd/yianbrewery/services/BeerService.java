package com.yesiamanerd.yianbrewery.services;

import com.yesiamanerd.yianbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
}
