package com.getir.demo.readingIsGood.controller;

import com.getir.demo.readingIsGood.TestBuilder;
import com.getir.demo.readingIsGood.entities.BookOrder;
import com.getir.demo.readingIsGood.entities.Report;
import com.getir.demo.readingIsGood.services.order.OrderService;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatisticsControllerTest {
    @InjectMocks
    private StatisticsController statisticsController;
    @Mock
    private OrderService orderService;


    @Before
    public void before() {
    }

    @Test
    public void testgetStatistics() {
        Mockito.when(orderService.getStatsReport(1L)).thenReturn(TestBuilder.createReport(1L));
        ResponseEntity<List<Report>> response = statisticsController.getStatistics("1");
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testgetStatistics_failed1() {
        ResponseEntity<List<Report>> response = statisticsController.getStatistics("");
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    public void testgetStatistics_failed2() {
        ResponseEntity<List<Report>> response = statisticsController.getStatistics(null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    public void testgetStatistics_failed3() {
        Mockito.when(orderService.getStatsReport(1L)).thenReturn(null);
        ResponseEntity<List<Report>> response = statisticsController.getStatistics("1");
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}


