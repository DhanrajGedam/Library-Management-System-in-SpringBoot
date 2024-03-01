package net.javaguides.library_management.Service;

import net.javaguides.library_management.Entity.Book;
import net.javaguides.library_management.Repositaory.BookRepo;

import java.util.List;

public interface BookSer {

    List<Book> findAll();
    Book findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
    List<Book> search(String query);



}