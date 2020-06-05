package com.yesiamanerd.yianbrewery.services;

import com.yesiamanerd.yianbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    CustomerDto getCustomerById(UUID id);
}
