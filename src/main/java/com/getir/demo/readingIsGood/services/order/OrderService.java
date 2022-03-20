package com.getir.demo.readingIsGood.services.order;

import com.getir.demo.readingIsGood.entities.BookOrder;
import com.getir.demo.readingIsGood.entities.Report;
import com.getir.demo.readingIsGood.model.ReqOrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Page<BookOrder> getCustomersOrder(Long customerId, Pageable pageable) ;
    BookOrder saveOrder(ReqOrderModel order);
    Optional<BookOrder> getOrder(Long orderId);
    List<BookOrder> getOrdersByDate(LocalDate startDate, LocalDate endDate,Pageable pageable);
    List<Report> getStatsReport(Long customerId);
}
