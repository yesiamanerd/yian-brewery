package com.yesiamanerd.yianbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yesiamanerd.yianbrewery.services.CustomerService;
import com.yesiamanerd.yianbrewery.web.model.CustomerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerDto validCustomer;

    @Before
    public void setUp(){
        validCustomer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .customerName("Paul Small")
                .build();
    }

    @Test
    public void getBeer() throws Exception {
        given(customerService.getCustomerById(any(UUID.class))).willReturn(validCustomer);

        mockMvc.perform(get("/api/v1/customer/" + validCustomer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
                .andExpect(jsonPath("$.customerName", is("Paul Small")));
    }

    @Test
    public void handlePost() throws Exception {
        //given
        CustomerDto customerDto = validCustomer;
        customerDto.setId(null);
        CustomerDto savedDto = CustomerDto.builder().id(UUID.randomUUID()).customerName("New Customer").build();
        String beerDtoJson = objectMapper.writeValueAsString(customerDto);

        given(customerService.saveNewCustomer(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void handleUpdate() throws Exception {
        //given
        CustomerDto customerDto = validCustomer;
        String beerDtoJson = objectMapper.writeValueAsString(customerDto);

        //when
        mockMvc.perform(put("/api/v1/customer/" + validCustomer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(customerService).should().updateCustomer(any(), any());

    }
}