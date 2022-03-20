package com.getir.demo.readingIsGood.repositories;

import com.getir.demo.readingIsGood.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long id);
}
