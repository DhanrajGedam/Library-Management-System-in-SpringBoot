package net.javaguides.library_management.Service;

import lombok.AllArgsConstructor;
import net.javaguides.library_management.Entity.Borrow;
import net.javaguides.library_management.Repositaory.BorrowRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;

import java.util.List;
@Service
@AllArgsConstructor
public class BorrowImpl implements BorrowSer{
    BorrowRepo borrowRepository;
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Borrow> findAll() {
        return borrowRepository.findAll();
    }

    @Override
    public Borrow findById(Long id) {
        return borrowRepository.findById(id).orElse(null);
    }

    @Override
    public Borrow save(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    @Override
    public void deleteById(Long id) {
        borrowRepository.deleteById(id);

    }

    @Override
    public void lendBook(Long memberId, Long bookId) {
        String checkAvailabilitySql = "SELECT available_copies FROM books WHERE id = ?";
        int availableCopies = jdbcTemplate.queryForObject(checkAvailabilitySql, new Object[]{bookId}, Integer.class);

        if (availableCopies > 0) {
            // Decrement available copies
            String updateBookSql = "UPDATE books SET available_copies = available_copies - 1 WHERE id = ?";
            jdbcTemplate.update(updateBookSql, bookId);

            // Create a new borrow record
            String insertBorrowSql = "INSERT INTO borrows (member_id, book_id, borrowed_date, due_date) VALUES (?, ?, ?, ?)";
            Date borrowedDate = new Date();
            Date dueDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
            jdbcTemplate.update(insertBorrowSql, memberId, bookId, borrowedDate, dueDate);
        } else {
            throw new RuntimeException("Book is not available for borrowing.");
        }
    }

    @Override
    public void returnBook(Long borrowId) {
        // Find the borrow record
        String findBorrowSql = "SELECT * FROM borrows WHERE id = ?";
        Borrow borrow = jdbcTemplate.queryForObject(findBorrowSql, new Object[]{borrowId}, (resultSet, rowNum) -> {
            Borrow b = new Borrow();
            b.setId(resultSet.getLong("id"));
            b.setMemberId(resultSet.getLong("member_id"));
            b.setBookId(resultSet.getLong("book_id"));
            b.setBorrowedDate(resultSet.getDate("borrowed_date"));
            b.setDueDate(resultSet.getDate("due_date"));
            return b;
        });

        if (borrow != null) {
            // Increment available copies
            String updateBookSql = "UPDATE books SET available_copies = available_copies + 1 WHERE id = ?";
            jdbcTemplate.update(updateBookSql, borrow.getBookId());

            // Update the borrow record
            String updateBorrowSql = "UPDATE borrows SET returned_date = ? WHERE id = ?";
            Date returnedDate = new Date();
            jdbcTemplate.update(updateBorrowSql, returnedDate, borrowId);
        } else {
            throw new RuntimeException("Borrow record not found.");
        }
    }

    }

