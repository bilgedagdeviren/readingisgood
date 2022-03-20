package com.getir.demo.readingIsGood.controller;


import com.getir.demo.readingIsGood.entities.Book;
import com.getir.demo.readingIsGood.exceptions.StockException;
import com.getir.demo.readingIsGood.services.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/book")
@Slf4j
public class BookController {


    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book restBook){
        log.info("Requested add new book {}", restBook);
        if (restBook == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookService.add(restBook), HttpStatus.CREATED);
    }

    @PutMapping("/update/{bookId}/{count}")
    public ResponseEntity<Book> updateBookStock(@PathVariable("bookId") String bookId, @PathVariable("count") String count){
        log.info("Requested update book stock:");
        if (bookId.isBlank())return new ResponseEntity("Book Id can't be blank.", HttpStatus.BAD_REQUEST);
        if (count.isBlank())return new ResponseEntity("Count can't be blank.", HttpStatus.BAD_REQUEST);

        Long id;
        Integer countInt;
        try{
            id = Long.parseLong(bookId);
            countInt = Integer.parseInt(count);
            return new ResponseEntity<>(bookService.updateStock(id, countInt),HttpStatus.OK);
        } catch (NumberFormatException e){
            return new ResponseEntity("Book id and count should be integer!",HttpStatus.BAD_REQUEST);
        }  catch (StockException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.debug("ERROR:",e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Book>> getAllBooks(){
        log.info("Requested get all books");
        List<Book> allBooks = bookService.getAll();
        if (allBooks == null){
            return new ResponseEntity(List.of(), HttpStatus.OK);
        } else{
            return ResponseEntity.ok(allBooks);
        }
    }
}
