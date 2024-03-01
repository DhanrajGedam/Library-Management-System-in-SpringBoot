package net.javaguides.library_management.Service;

import net.javaguides.library_management.Entity.Member;

import java.util.List;

public interface MemberSer {
    List<Member> findAll();
    Member findById(Long id);
    Member save(Member member);
    void deleteById(Long id);
}
