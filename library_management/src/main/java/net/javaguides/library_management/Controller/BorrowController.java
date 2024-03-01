package net.javaguides.library_management.Controller;

import lombok.AllArgsConstructor;
import net.javaguides.library_management.Entity.Borrow;
import net.javaguides.library_management.Service.BorrowSer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/borrow")
public class BorrowController {

    BorrowSer borrowService;

    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowService.findAll();
    }

    @PostMapping
    public Borrow createBorrow(@RequestBody Borrow borrow) {
        return borrowService.save(borrow);
    }

    @PutMapping("/{id}")
    public Borrow updateBorrow(@PathVariable Long id, @RequestBody Borrow borrowDetails) {
        return borrowService.save(borrowDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrow(@PathVariable Long id) {
        borrowService.deleteById(id);
    }

    @PostMapping("/lend")
    public void lendBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        borrowService.lendBook(memberId, bookId);
    }

    @PostMapping("/return")
    public void returnBook(@RequestParam Long borrowId) {
        borrowService.returnBook(borrowId);
    }

}
