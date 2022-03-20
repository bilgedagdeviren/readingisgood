package com.getir.demo.readingIsGood.services.order;

import com.getir.demo.readingIsGood.entities.Book;
import com.getir.demo.readingIsGood.entities.BookOrder;
import com.getir.demo.readingIsGood.entities.Customer;
import com.getir.demo.readingIsGood.entities.Report;
import com.getir.demo.readingIsGood.model.ReqOrderModel;
import com.getir.demo.readingIsGood.repositories.BookRepository;
import com.getir.demo.readingIsGood.repositories.CustomerRepository;
import com.getir.demo.readingIsGood.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private BookRepository bookRepository;
    private CustomerRepository customerRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, BookRepository bookRepository , CustomerRepository customerRepository ) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.customerRepository=customerRepository;
    }

    @Override
    public Page<BookOrder> getCustomersOrder(Long customerId, Pageable pageable) {
        return orderRepository.findBookOrdersByCustomerId(customerId, pageable);
    }

    @Override
    @Transactional
    public BookOrder saveOrder(ReqOrderModel reqOrderModel) {
        BookOrder order = new BookOrder();
        Book book = bookRepository.getById(reqOrderModel.getBookId());
        Customer customer = customerRepository.findByEmail(reqOrderModel.getEmail());
        if(book.getCount() < reqOrderModel.getQuantity()) {
            throw new IllegalArgumentException("Ordered Count of books not available");
        }
        order.setBook(book);
        order.setCustomer(customer);
        order.setDate(new Date(System.currentTimeMillis()));
        order.setQuantity(reqOrderModel.getQuantity());
        orderRepository.saveAndFlush(order);
        book.setCount(book.getCount() - order.getQuantity());
        bookRepository.saveAndFlush(book);
        return order;
    }

    @Override
    public Optional<BookOrder> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<BookOrder> getOrdersByDate(LocalDate startDate, LocalDate endDate,Pageable pageable) {
        var start=java.sql.Date.valueOf(startDate);
        var end=java.sql.Date.valueOf(endDate);
        return orderRepository.findBookOrdersBetweenMonths(start,end,pageable);
    }

    public List<Report> getStatsReport(Long customerId) {
        return orderRepository.getReport(customerId);
    }
}