package com.getir.demo.readingIsGood.services.customer;

import com.getir.demo.readingIsGood.TestBuilder;
import com.getir.demo.readingIsGood.entities.Book;
import com.getir.demo.readingIsGood.entities.Customer;
import com.getir.demo.readingIsGood.model.ReqCustomerModel;
import com.getir.demo.readingIsGood.repositories.BookRepository;
import com.getir.demo.readingIsGood.repositories.CustomerRepository;
import com.getir.demo.readingIsGood.services.book.BookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTest {
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;
    private Customer customer;

    @Before
    public void before() {
        customer = TestBuilder.getCustomer(1L);
    }

    @Test
    public void testAdd() {
        Mockito.when(customerRepository.saveAndFlush(customer)).thenReturn(customer);
        Mockito.when(customerRepository.findByEmail(TestBuilder.getCustomer().getEmail())).thenReturn(customer);
        Assert.assertNotNull(customerService.add(TestBuilder.getCustomer()));
    }
}
