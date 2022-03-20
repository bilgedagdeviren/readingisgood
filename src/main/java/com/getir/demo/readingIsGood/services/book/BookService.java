package com.getir.demo.readingIsGood.services.book;

import com.getir.demo.readingIsGood.entities.Book;

import java.util.List;

public interface BookService {
    Book add(Book book);

    List<Book> getAll();

    Book updateStock(Long id, Integer countInt) throws Exception;

    Book get(Long id);
}
