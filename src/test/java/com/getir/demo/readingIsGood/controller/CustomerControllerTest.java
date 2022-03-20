package com.getir.demo.readingIsGood.controller;

import com.getir.demo.readingIsGood.TestBuilder;
import com.getir.demo.readingIsGood.entities.BookOrder;
import com.getir.demo.readingIsGood.entities.Customer;
import com.getir.demo.readingIsGood.model.ReqCustomerModel;
import com.getir.demo.readingIsGood.services.customer.CustomerService;
import com.getir.demo.readingIsGood.services.order.OrderService;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Mock
    OrderService orderService;

    @Before
    public void before() {
    }
    @Test
    public void testAdd() {
        ReqCustomerModel  customer = TestBuilder.getCustomer();
        Mockito.when(customerService.add(customer)).thenReturn(customer.toCustomerEntity());
        ResponseEntity<Customer> response = customerController.addCustomer(customer);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void addCustomer_Failed() throws Exception {
        ReqCustomerModel  customer = TestBuilder.getCustomer();
        customer.setEmail("");
        ResponseEntity<Customer> response = customerController.addCustomer(customer);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testgetCustomerOrders() throws Exception {
        Mockito.when(orderService.getCustomersOrder(1L,null)).thenReturn(new PageImpl<BookOrder>(TestBuilder.getBookOrderList()));
        ResponseEntity<Page<BookOrder>>  response = customerController.getCustomerOrders("1",null);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getCustomerOrders_Failed() throws Exception {

        ResponseEntity<Page<BookOrder>>  response = customerController.getCustomerOrders("",null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
