package service;

import entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.JdbcMemberRepository;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private JdbcMemberRepository jdbcMemberRepository;

    public List<Member> adminLogin(Member member) {
        return jdbcMemberRepository.getMember(member);
    }

    public String getNameById(int id) {
        return jdbcMemberRepository.getNameById(id);
    }

    public List<Member> getAll() {
        return jdbcMemberRepository.getAll();
    }

    public void insertMember(Member member) {
        jdbcMemberRepository.insert(member);
    }

    public void changeToAdmin(int id) {
        jdbcMemberRepository.changeToAdmin(id);
    }

    public void updateEmail(int id, String email) {
        jdbcMemberRepository.updateEmail(id,email);
    }

    public void deleteAdmin(int id) {
        jdbcMemberRepository.deleteAdmin(id);
    }
}
