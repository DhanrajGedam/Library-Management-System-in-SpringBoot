package net.javaguides.library_management.Repositaory;

import net.javaguides.library_management.Entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepo extends JpaRepository<Borrow,Long> {
}
