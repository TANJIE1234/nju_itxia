package repository;

import entity.Member;

import java.util.List;

public interface MemberRepository {
    List<Member> getMember(Member member);

    List<Member> getAll();

    List<Member> getByLocation(String location);

    void insert(Member member);

    void deleteAdmin(int id);

    void updateEmail(int id, String email);

    void changeToAdmin(String account);
}
