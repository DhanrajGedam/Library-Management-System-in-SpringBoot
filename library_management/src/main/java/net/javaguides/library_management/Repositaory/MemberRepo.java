package net.javaguides.library_management.Repositaory;

import net.javaguides.library_management.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member,Long> {
}
