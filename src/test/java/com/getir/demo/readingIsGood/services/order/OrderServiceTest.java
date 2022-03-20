package com.getir.demo.readingIsGood.services.order;

import com.getir.demo.readingIsGood.TestBuilder;
import com.getir.demo.readingIsGood.entities.Book;
import com.getir.demo.readingIsGood.entities.BookOrder;
import com.getir.demo.readingIsGood.model.ReqOrderModel;
import com.getir.demo.readingIsGood.repositories.BookRepository;
import com.getir.demo.readingIsGood.repositories.CustomerRepository;
import com.getir.demo.readingIsGood.repositories.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @InjectMocks
    private OrderServiceImpl orderServiceImpl;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private CustomerRepository customerRepository;

    private ReqOrderModel model;

    @Before
    public void before() {
        model = TestBuilder.getReqOrderModel();
    }

    @Test
    public void testSaveOrder() {
        Mockito.when(bookRepository.getById(model.getBookId())).thenReturn(TestBuilder.getBook());
        Mockito.when(customerRepository.findByEmail(model.getEmail())).thenReturn(TestBuilder.getCustomer(1L));
        Mockito.when(bookRepository.saveAndFlush(any(Book.class))).thenReturn(TestBuilder.getBook());
        Mockito.when(orderRepository.saveAndFlush(any(BookOrder.class))).thenReturn(TestBuilder.getBookOrder());

        Assert.assertNotNull(orderServiceImpl.saveOrder(model));

    }
}