package entity;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;


@Component
public class Order {

    public Order() {
    }

    private int id;
    private Timestamp updatedon;
    private String phone;
    private String bbsid;
    private String email;
    private String location;
    private String model;
    private String os;
    private String desc;
    private String handlerName;
    private List<Reply> reply;

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", updatedon=" + updatedon +
                ", phone='" + phone + '\'' +
                ", bbsid='" + bbsid + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", model='" + model + '\'' +
                ", os='" + os + '\'' +
                ", desc='" + desc + '\'' +
                ", handlerName='" + handlerName + '\'' +
                ", reply=" + reply +
                ", handler=" + handler +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    private int handler;
    private String name;
    private int status;

    public Order(int id, Timestamp updatedon, String phone, String bbsid, String email, String location, String model, String os, String desc, int handler, String name, int status) {
        this.id = id;
        this.updatedon = updatedon;
        this.phone = phone;
        this.bbsid = bbsid;
        this.email = email;
        this.location = location;
        this.model = model;
        this.os = os;
        this.desc = desc;
        this.handler = handler;
        this.name = name;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.util.Date getUpdatedon() {
        return updatedon;
    }

    public void setUpdatedon(Timestamp updatedon) {
        this.updatedon = updatedon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBbsid() {
        return bbsid;
    }

    public void setBbsid(String bbsid) {
        this.bbsid = bbsid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Reply> getReply() {
        return reply;
    }

    public void setReply(List<Reply> reply) {
        this.reply = reply;
    }

    public int getHandler() {
        return handler;
    }

    public void setHandler(int handler) {
        this.handler = handler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
