package net.javaguides.library_management.Service;

import lombok.AllArgsConstructor;
import net.javaguides.library_management.Entity.Member;
import net.javaguides.library_management.Repositaory.MemberRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MemberImpl implements MemberSer {

    MemberRepo memberRepository;
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

}
