package com.getir.demo.readingIsGood.controller;

import com.getir.demo.readingIsGood.entities.BookOrder;
import com.getir.demo.readingIsGood.model.ReqOrderModel;
import com.getir.demo.readingIsGood.services.order.OrderService;
import com.getir.demo.readingIsGood.util.HelperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@RestController
@RequestMapping(value="/v1/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<BookOrder> createOrder(@RequestBody ReqOrderModel createOrderModel) {
        log.info("Requested for Order creation {}", createOrderModel);
        return new ResponseEntity<>(orderService.saveOrder(createOrderModel), HttpStatus.CREATED);
    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<BookOrder> getOrder(@PathVariable("orderId") String orderId){
        log.info("Requested get Order");
        if (orderId.isBlank())return new ResponseEntity("Order Id can't be blank.", HttpStatus.BAD_REQUEST);

        Long orderIdConverted;
        try{
            orderIdConverted = Long.parseLong(orderId);
        } catch (NumberFormatException e){
            return new ResponseEntity("Order id is not valid!",HttpStatus.BAD_REQUEST);
        }
        Optional<BookOrder> order = orderService.getOrder(orderIdConverted);
        if (!order.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            return ResponseEntity.ok(order.get());
        }
    }

    @GetMapping("/getByDateInterval")
    public ResponseEntity<Page<BookOrder>> getOrderByDate(@RequestParam("startdate") String startdate, @RequestParam("endDate") String enddate, final Pageable pageable){
        log.info("Requested get get Order By Date");

        if (startdate.isBlank())return new ResponseEntity("Start day can't be blank.", HttpStatus.BAD_REQUEST);
        if (enddate.isBlank())return new ResponseEntity("End day can't be blank.", HttpStatus.BAD_REQUEST);
        try {
            LocalDate start = HelperUtils.convertLocalDate(startdate);
            LocalDate end =  HelperUtils.convertLocalDate(enddate);
            if(start.compareTo(end)>0)
                return new ResponseEntity("Start date can not be greater than end date!",HttpStatus.BAD_REQUEST);
            else{
                var list= orderService.getOrdersByDate(start, end,pageable);
                ResponseEntity.ok(list);
            }
        }catch (DateTimeParseException e){
            return new ResponseEntity("Start date and end date is not valid! ddMMyyyy is format.",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
                return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
