package service;

import entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.JdbcReplyRepository;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private JdbcReplyRepository jdbcReplyRepository;

    public boolean addAdminReply(int orderId, int itxiaId,String content) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        return jdbcReplyRepository.addReply(new Reply(1,orderId,itxiaId,timestamp,content));
    }

    public boolean addHelperReply(int orderId,String content) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        return jdbcReplyRepository.addReply(new Reply(0,orderId,0,timestamp,content));
    }

    public boolean checkIsPoster(int replyId,int itxiaId) {
        return jdbcReplyRepository.checkIndexItxia(replyId, itxiaId);
    }

    public boolean delReply(int replyId) {
        return jdbcReplyRepository.delReply(replyId);
    }

    List<Reply> getReply(int orderId) {
        return jdbcReplyRepository.getReply(orderId);
    }
}
