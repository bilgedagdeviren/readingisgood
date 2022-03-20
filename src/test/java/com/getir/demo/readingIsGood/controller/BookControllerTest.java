package com.getir.demo.readingIsGood.controller;

import com.getir.demo.readingIsGood.TestBuilder;
import com.getir.demo.readingIsGood.entities.Book;
import com.getir.demo.readingIsGood.services.book.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Before
    public void before() {
    }

    @Test
    public void testAdd() {
        Book  bookDTO = TestBuilder.getBook();
        Mockito.when(bookService.add(bookDTO)).thenReturn(bookDTO);
        ResponseEntity<Book> response = bookController.addBook(bookDTO);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void addBook_Failed() throws Exception {
        ResponseEntity<Book> response = bookController.addBook(null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdateStock() throws Exception {

        Book  bookDTO = TestBuilder.getBook();
        Mockito.when(bookService.updateStock(1L,10)).thenReturn(bookDTO);
        ResponseEntity<Book> response =  bookController.updateBookStock("1","10");
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateStock_failed() throws Exception {
        ResponseEntity<Book> response =  bookController.updateBookStock("","10");
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    public void testUpdateStock_failed2() throws Exception {
        ResponseEntity<Book> response =  bookController.updateBookStock("1","");
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
