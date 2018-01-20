package repository;

import entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class JdbcMemberRepository implements MemberRepository {
    private JdbcOperations jdbcOperations;
    private static final String SQL_INSERT_MEMBER = "insert into members (name, account, email, password, location,admin) values (?,?,?,?,?,?)";
    private static final String SQL_GET_MEMBER = "select * from members where account = ? AND password = ?";
    private static final String SQL_GET_MEMBER_BY_LOCATION = "select * from members where location = ?";
    private static final String SQL_GET_ALL_MEMBER = "select * from members";
    private static final String SQL_DELETE_KNIGHT = "delete from members where id = ?";
    private static final String SQL_UPDATE_EMAIL = "update members set email = ? where id = ?";
    private static final String SQL_CHANGE_ADMIN = "update members set admin = 1 where account = ?";
    private static final String SQL_GET_NAME_BY_ID= "select `name` from members where id = ?";

    @Autowired
    public JdbcMemberRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public List<Member> getMember(Member member) {
        List<Member> checkedMember = jdbcOperations.query(SQL_GET_MEMBER, new UserRowMapper(), member.getAccount(), member.getPassword());
        return checkedMember;
    }

    public List<Member> getAll() {
        return jdbcOperations.query(SQL_GET_ALL_MEMBER, new UserRowMapper());
    }

    public List<Member> getByLocation(String location) {
        return jdbcOperations.query(SQL_GET_MEMBER_BY_LOCATION, new UserRowMapper(),location);
    }

    public void insert(Member member) {
        jdbcOperations.update(SQL_INSERT_MEMBER, member.getUsername(), member.getAccount(), member.getEmail(), member.getPassword(), member.getLocation(),0);
    }

    public void deleteAdmin(int id) {
        jdbcOperations.update(SQL_DELETE_KNIGHT, id);
    }

    public void updateEmail(int id, String email) {
        jdbcOperations.update(SQL_UPDATE_EMAIL, email, id);
    }

    public void changeToAdmin(String account) {
        jdbcOperations.update(SQL_CHANGE_ADMIN, account);
    }

    public String getNameById(int id) {
        return jdbcOperations.queryForObject(SQL_GET_NAME_BY_ID, String.class, id);
    }

    private static final class UserRowMapper implements org.springframework.jdbc.core.RowMapper<Member> {
        public Member mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Member(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("account"),
                    resultSet.getString("password"),
                    resultSet.getString("location"),
                    resultSet.getInt("admin"),
                    resultSet.getString("email")
            );
        }
    }
}
