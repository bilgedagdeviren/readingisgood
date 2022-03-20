package com.getir.demo.readingIsGood.services.book;

import com.getir.demo.readingIsGood.TestBuilder;
import com.getir.demo.readingIsGood.entities.Book;
import com.getir.demo.readingIsGood.exceptions.StockException;
import com.getir.demo.readingIsGood.repositories.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookServiceTests {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository bookRepository;
    private Book book;

    private static final Integer BOOK_COUNT = 10;

    @Before
    public void before() {
        book = TestBuilder.getBook();
    }

    @Test
    public void testAddBook() {
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Assert.assertNotNull(bookService.add(book));
    }

    @Test
    public void testGet() {
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Assert.assertNotNull(bookService.get(1L));
    }

    @Test
    public void testUpdateStock() throws Exception {
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Assert.assertNotNull(bookService.updateStock(1L, 1));
    }
    @Test(expected = Exception.class)
    public void testUpdateStock_Exception() throws Exception {
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        bookService.updateStock(1L, 1);
    }

}