package net.javaguides.library_management.Service;

import lombok.AllArgsConstructor;
import net.javaguides.library_management.Entity.Book;
import net.javaguides.library_management.Repositaory.BookRepo;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class BookImpl implements BookSer{
    private BookRepo repo;
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Book book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setPublishedDate(resultSet.getDate("published_date"));
            book.setAvailableCopies(resultSet.getInt("available_copies"));
            return book;
        });
    }

    @Override
    public Book findById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, rowNum) -> {
            Book book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setPublishedDate(resultSet.getDate("published_date"));
            book.setAvailableCopies(resultSet.getInt("available_copies"));
            return book;
        });
    }

    @Override
    public Book save(Book book) {
        String sql = "INSERT INTO books (title, author, isbn, published_date, available_copies) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublishedDate(), book.getAvailableCopies());
        return book;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Book> search(String query) {
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR isbn LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + query + "%", "%" + query + "%", "%" + query + "%"}, (resultSet, rowNum) -> {
            Book book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setPublishedDate(resultSet.getDate("published_date"));
            book.setAvailableCopies(resultSet.getInt("available_copies"));
            return book;
        });
    }
}
