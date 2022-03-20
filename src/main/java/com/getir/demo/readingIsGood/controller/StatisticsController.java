package com.getir.demo.readingIsGood.controller;

import com.getir.demo.readingIsGood.entities.Report;
import com.getir.demo.readingIsGood.services.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value="/v1/statistics")
@Validated
@Slf4j
public class StatisticsController {

    @Autowired
    OrderService orderService;

    @GetMapping("/monthly/{customerId}")
    public ResponseEntity<List<Report>> getStatistics(@PathVariable("customerId") String customerId){
        log.info("Requested get Statistics");
        if (customerId == null || customerId.isBlank())return new ResponseEntity("Customer Id can't be blank.", HttpStatus.BAD_REQUEST);

        Long customerIdConverted;
        try{
            customerIdConverted = Long.parseLong(customerId);
        } catch (NumberFormatException e){
            return new ResponseEntity("customerId id is not valid!",HttpStatus.BAD_REQUEST);
        }
        List<Report> montlyOrderStatistics = orderService.getStatsReport(customerIdConverted);
        if (montlyOrderStatistics == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(montlyOrderStatistics);
    }

}