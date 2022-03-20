package com.getir.demo.readingIsGood;

import com.getir.demo.readingIsGood.entities.Book;
import com.getir.demo.readingIsGood.entities.BookOrder;
import com.getir.demo.readingIsGood.entities.Customer;
import com.getir.demo.readingIsGood.entities.Report;
import com.getir.demo.readingIsGood.model.ReqCustomerModel;
import com.getir.demo.readingIsGood.model.ReqOrderModel;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TestBuilder {

    public static Book getBook(){
        Book book = new Book();
        book.setName("Sapiens: A Brief History of Humankind");
        book.setAuthor("Yuval Noah Harari");
        book.setPrice(5.50);
        book.setCount(10);
        book.setYear(2011);
        book.setId(1L);
        return book;
    }

    public static ReqCustomerModel getCustomer(){
        ReqCustomerModel cust = new ReqCustomerModel();
        cust.setName("Bilge");
        cust.setEmail("bilge@bilge.com");
        return  cust;
    }
    public static Customer getCustomer(Long id){
        ReqCustomerModel model= getCustomer();
        Customer customer= model.toCustomerEntity();
        customer.setId(id);
        return customer;
    }

    public static BookOrder getBookOrder(){
        BookOrder bookOrder = new BookOrder();
        bookOrder.setBook(getBook());
        bookOrder.setCustomer(getCustomer(1L));
        bookOrder.setQuantity(1);
        bookOrder.setId(1L);
        bookOrder.setDate(new Date(123332115L));
        return  bookOrder;
    }


    public static List<BookOrder> getBookOrderList(){
        List<BookOrder> newList=new ArrayList<>();
        newList.add(getBookOrder());

        return  newList;
    }


    public static ReqOrderModel getReqOrderModel(){
        return new ReqOrderModel();
    }

public static   List<Report> createReport(Long customerId){
List<Report> list = new ArrayList<>();
list.add(new Report() {
    @Override
    public String getMonth() {
        return "March";
    }

    @Override
    public Long getOrdersCount() {
        return 1L;
    }

    @Override
    public Long getBooksCount() {
        return 1L;
    }

    @Override
    public Double getTotalPurchasedAmount() {
        return 10.0;
    }
});

return list;
}
}
