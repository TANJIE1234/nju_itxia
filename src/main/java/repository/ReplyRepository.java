package repository;

import entity.Reply;

import java.util.List;

public interface ReplyRepository {
    List<Reply> getReply(int orderId);

    boolean addReply(Reply reply);

    boolean checkIndexItxia(int replyId,int itxiaId);

    boolean delReply(int replyId);
}
