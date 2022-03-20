package com.getir.demo.readingIsGood.controller;

import com.getir.demo.readingIsGood.TestBuilder;
import com.getir.demo.readingIsGood.entities.BookOrder;
import com.getir.demo.readingIsGood.model.ReqOrderModel;
import com.getir.demo.readingIsGood.services.order.OrderService;
import com.getir.demo.readingIsGood.util.HelperUtils;
import org.junit.Assert;
import org.junit.Before;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderControllerTest {


    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;


    @Before
    public void before() {
    }

    @Test
    public void testAdd() {
        ReqOrderModel model = TestBuilder.getReqOrderModel();
        Mockito.when(orderService.saveOrder(model)).thenReturn(TestBuilder.getBookOrder());
        ResponseEntity<BookOrder> response = orderController.createOrder(model);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testgetOrder() {
        ReqOrderModel model = TestBuilder.getReqOrderModel();
        Mockito.when(orderService.getOrder(1L)).thenReturn(Optional.of(TestBuilder.getBookOrder()));
        ResponseEntity<BookOrder> response = orderController.getOrder("1");
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getOrder_Failed() throws Exception {
        ResponseEntity<BookOrder> response = orderController.getOrder("");
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testgetOrderByDate() throws ParseException {
        Mockito.when(orderService.getOrdersByDate(HelperUtils.convertLocalDate("01022022"),HelperUtils.convertLocalDate("01032022"),null)).thenReturn(TestBuilder.getBookOrderList());
        ResponseEntity<Page<BookOrder>> response = orderController.getOrderByDate("01022022","01032022",null);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getOrderByDate_Failed() throws Exception {
        ResponseEntity<Page<BookOrder>> response = orderController.getOrderByDate("022022","01032022",null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getOrderByDate_Failed2() throws Exception {
        ResponseEntity<Page<BookOrder>> response = orderController.getOrderByDate("01022022","0103022",null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void getOrderByDate_Failed3() throws Exception {
        ResponseEntity<Page<BookOrder>> response = orderController.getOrderByDate("01082022","01032022",null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
