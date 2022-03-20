package com.getir.demo.readingIsGood.controller;

import com.getir.demo.readingIsGood.entities.BookOrder;
import com.getir.demo.readingIsGood.entities.Customer;
import com.getir.demo.readingIsGood.model.ReqCustomerModel;
import com.getir.demo.readingIsGood.services.customer.CustomerService;
import com.getir.demo.readingIsGood.services.order.OrderService;
import com.getir.demo.readingIsGood.util.HelperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/v1/customer")
@Slf4j
public class CustomerController {


    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid ReqCustomerModel reqCustomer){
        log.info("Requested new customer for {} ", reqCustomer.getEmail());
        if(HelperUtils.isValidEmailId(reqCustomer.getEmail()))
            return new ResponseEntity<>(customerService.add(reqCustomer), HttpStatus.CREATED);
        else
            return new ResponseEntity("Customer email is not valid!",HttpStatus.BAD_REQUEST);

    }

    // get all customers
    @GetMapping(path = "/get")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        log.info("Requested all customer info");
        List<Customer> allCustomers = customerService.getAll();
        if (allCustomers == null){
            return ResponseEntity.ok(null);
        } else{
            return ResponseEntity.ok(allCustomers);
        }
    }

    @GetMapping(path = "/getOrders/{customerId}")
    public ResponseEntity <Page<BookOrder>>  getCustomerOrders(@PathVariable("customerId") String customerId, final Pageable pageable) {
        log.info("Requested customer orders info");
        if (customerId.isBlank())return new ResponseEntity("Customer Id can't be blank.", HttpStatus.BAD_REQUEST);

        Long customerIdConverted;
        try{
            customerIdConverted = Long.parseLong(customerId);
        } catch (NumberFormatException e){
            return new ResponseEntity("Customer id is not valid!",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderService.getCustomersOrder(customerIdConverted, pageable), HttpStatus.OK);
    }
}
