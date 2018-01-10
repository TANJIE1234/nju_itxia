package entity;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Reply implements Comparable{
    private int id;
    private int replybool;
    private int orderId;
    private int itxiaId;
    private Timestamp time;
    private String content;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Reply() {
    }

    public Reply(int replybool, int orderId, int itxiaId, Timestamp time, String content) {
        this.replybool = replybool;
        this.orderId = orderId;
        this.itxiaId = itxiaId;
        this.time = time;
        this.content = content;
    }

    public Reply(int id, int replybool, int orderId, int itxiaId, Timestamp time, String content) {

        this.id = id;
        this.replybool = replybool;
        this.orderId = orderId;
        this.itxiaId = itxiaId;
        this.time = time;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReplybool() {
        return replybool;
    }

    public void setReplybool(int replybool) {
        this.replybool = replybool;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItxiaId() {
        return itxiaId;
    }

    public void setItxiaId(int itxiaId) {
        this.itxiaId = itxiaId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int compareTo(Object o) {
        Reply reply = (Reply) o;
        return this.getId() - reply.getId();
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", replybool=" + replybool +
                ", orderId=" + orderId +
                ", itxiaId=" + itxiaId +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
