package com.getir.demo.readingIsGood.services.book;

import com.getir.demo.readingIsGood.entities.Book;
import com.getir.demo.readingIsGood.exceptions.StockException;
import com.getir.demo.readingIsGood.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateStock(Long id, Integer countInt) throws Exception {
        Optional<Book> bookById = bookRepository.findById(id);
        if (bookById.isPresent() && bookById.get().getCount() + countInt < 0)
            throw new StockException("Out of Stock, more deleted from Stock!");
        else if (!bookById.isPresent()){
            throw new IllegalStateException("Book is not exist!");
        }
        Book book = bookById.get();
        book.setCount(book.getCount()+countInt);
        return bookRepository.save(book);
    }

    @Override
    public Book get(Long id) {
        Optional<Book> bookById = bookRepository.findById(id);
        return bookById.orElse(null);
    }
}
