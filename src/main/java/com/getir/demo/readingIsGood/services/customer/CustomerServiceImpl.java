package com.getir.demo.readingIsGood.services.customer;

import com.getir.demo.readingIsGood.entities.Customer;
import com.getir.demo.readingIsGood.model.ReqCustomerModel;
import com.getir.demo.readingIsGood.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements  CustomerService{

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer add(ReqCustomerModel reqCustomer) {
        Customer customer = reqCustomer.toCustomerEntity();
        customerRepository.saveAndFlush(customer);
        return customerRepository.findByEmail(reqCustomer.getEmail());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAll() {
        return  customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
