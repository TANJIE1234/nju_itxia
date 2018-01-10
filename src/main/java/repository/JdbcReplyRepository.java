package repository;

import entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
public class JdbcReplyRepository implements ReplyRepository {
    private JdbcOperations jdbcOperations;

    private static final String SQL_GET_HELPER_REPLY = "SELECT `index`, `replybool`, `orderid`, `itxiaid`, `time`, `content` FROM `reply` WHERE `orderid`=? AND `replybool`= 0 ORDER BY `reply`.`index` ASC";
    private static final String SQL_GET_KNIGHT_REPLY = "SELECT `index`, `replybool`, `orderid`, `itxiaid`, `time`, `content`, `account` as `name` FROM `reply` JOIN `members` WHERE `itxiaid`=`id` AND `orderid`=? AND `replybool`= 1 ORDER BY `reply`.`index` ASC";
    private static final String SQL_ADD_REPLY = "INSERT INTO reply (replybool,orderid,itxiaid,time,content) VALUES (?,?,?,?,?)";
    private static final String SQL_CHECK_REPLY = "SELECT `index`  FROM reply WHERE `index` = ? AND itxiaid = ?";
    private static final String SQL_DEL_REPLY = "DELETE  FROM reply WHERE `index` = ?";

    @Autowired
    public JdbcReplyRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    public List<Reply> getReply(int orderId) {
        List<Reply> replyList = new ArrayList<Reply>();
        List<Reply> helperReplyList = jdbcOperations.query(SQL_GET_HELPER_REPLY, new ReplyRowMapper(), orderId);
        List<Reply> knightReplyList = jdbcOperations.query(SQL_GET_KNIGHT_REPLY, new ReplyRowMapper(), orderId);
        for (Reply reply : helperReplyList) {
            reply.setName("我");
        }
        replyList.addAll(helperReplyList);
        replyList.addAll(knightReplyList);
        Collections.sort(replyList);
        return replyList;
    }

    public boolean addReply(Reply reply) {
        int affect = jdbcOperations.update(SQL_ADD_REPLY,reply.getReplybool(),reply.getOrderId(),reply.getItxiaId(),reply.getTime(),reply.getContent());
        return affect == 1;
    }

    public boolean checkIndexItxia(int replyId, int itxiaId) {
        return (jdbcOperations.query(SQL_CHECK_REPLY, new ReplyRowMapper(), replyId,itxiaId).size()==1);

    }

    public boolean delReply(int replyId) {
        int affect = jdbcOperations.update(SQL_DEL_REPLY,replyId);
        return affect == 1;
    }

    private static final class ReplyRowMapper implements RowMapper<Reply> {
        public Reply mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Reply(resultSet.getInt("index"),
                    resultSet.getInt("replybool"),
                    resultSet.getInt("orderid"),
                    resultSet.getInt("itxiaid"),
                    resultSet.getTimestamp("time"),
                    resultSet.getString("content"));
        }
    }
}
