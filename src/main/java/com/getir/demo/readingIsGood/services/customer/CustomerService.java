package com.getir.demo.readingIsGood.services.customer;

import com.getir.demo.readingIsGood.entities.Customer;
import com.getir.demo.readingIsGood.model.ReqCustomerModel;

import java.util.List;

public interface CustomerService {
    Customer add(ReqCustomerModel customer);
    List<Customer> getAll();
    Customer findByEmail(String email);
}

