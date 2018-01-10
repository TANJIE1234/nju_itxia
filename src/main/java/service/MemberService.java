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
}
