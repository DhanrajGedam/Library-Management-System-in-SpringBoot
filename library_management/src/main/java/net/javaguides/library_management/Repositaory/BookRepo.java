package net.javaguides.library_management.Repositaory;

import net.javaguides.library_management.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Long> {
}
