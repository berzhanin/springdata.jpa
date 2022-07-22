package com.springdata.jpa.repositories;

import com.springdata.jpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
