package com.springdata.jpa.bootstrap;

import com.springdata.jpa.domain.Book;
import com.springdata.jpa.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse");
        System.out.println("Id: " + bookDDD.getId());
        Book savedDDD = bookRepository.save(bookDDD);
        System.out.println("Id: " + savedDDD.getId());

        Book bookSIA = new Book("Spring In Action", "234234", "Oriely");
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("Book Id: " + book.getId());
            System.out.println("Book Tittle: " + book.getTitle());
        });
    }
}
