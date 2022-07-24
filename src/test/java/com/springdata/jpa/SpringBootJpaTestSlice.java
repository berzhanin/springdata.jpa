package com.springdata.jpa;

import com.springdata.jpa.domain.Book;
import com.springdata.jpa.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = {"com.springdata.jpa.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaTestSlice {

    @Autowired
    BookRepository bookRepository;

    @Test
    @Commit
    @Order(1)
    @Rollback(value = false)
    void testJpaTestSlice() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

        bookRepository.save(new Book("My Book", "123456", "Self"));
        long countAfter = bookRepository.count();
        assertThat(countBefore).isLessThan(countAfter);
    }

    @Test
    @Order(2)
    void testJpaTestSliceTransaction() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);
    }
}
