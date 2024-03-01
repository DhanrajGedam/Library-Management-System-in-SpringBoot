package net.javaguides.library_management.Service;

import net.javaguides.library_management.Entity.Borrow;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BorrowSer {
    List<Borrow> findAll();
    Borrow findById(Long id);
    Borrow save(Borrow borrow);
    void deleteById(Long id);
    void lendBook(Long memberId, Long bookId);
    void returnBook(Long borrowId);
}
