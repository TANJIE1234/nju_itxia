package service;

import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.JdbcOrderRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private JdbcOrderRepository jdbcOrderRepository;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private MemberService memberService;

    public List<Order> getWait(String location) {
        List<Order> orderList = jdbcOrderRepository.getWait(location);
        for (Order order : orderList) {
            order.setReply(replyService.getReply(order.getId(), "admin"));
        }
        return orderList;
    }

    public List<Order> getWork(String location) {
        List<Order> orderList = jdbcOrderRepository.getWork(location);
        for (Order order : orderList) {
            order.setReply(replyService.getReply(order.getId(), "admin"));
            order.setHandlerName(memberService.getNameById(order.getHandler()));
        }
        return orderList;
    }

    public List<Order> getFinish(String location, int page) {
        List<Order> orderList = jdbcOrderRepository.getFinish(location, page, 1);
        for (Order order : orderList) {
            order.setReply(replyService.getReply(order.getId(), "admin"));
            order.setHandlerName(memberService.getNameById(order.getHandler()));
        }
        return orderList;
    }

    public List<Order> getHelperOrder(String phone) {
        List<Order> orderList = jdbcOrderRepository.getHelperOrder(phone);
        for (Order order : orderList) {
            order.setReply(replyService.getReply(order.getId(), "helper"));
        }
        return orderList;
    }

    public boolean addOrder(Order order) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        order.setUpdatedon(timestamp);
        return jdbcOrderRepository.addOrder(order);
    }

    public boolean modifyOrder(Order oldOrder) {
        return jdbcOrderRepository.modifyOrder(oldOrder);
    }

    public Order getOrder(int oid) {
        Order order = jdbcOrderRepository.getOrder(oid);
        order.setReply(replyService.getReply(oid, "helper"));
        return order;
    }

    public boolean delOrder(int oid) {
        return jdbcOrderRepository.delOrder(oid);
    }

    public boolean changeToWork(int oid, int handler) {
        return jdbcOrderRepository.changeOrderStatus(oid, handler, 1);
    }

    public boolean changeToWait(int oid, int handler) {
        return jdbcOrderRepository.changeOrderStatus(oid, handler, 0);
    }

    public boolean changeToFinish(int oid, int handler) {
        return jdbcOrderRepository.changeOrderStatus(oid, handler, 2);
    }

    public int[] getOrderCount(String location) {
        return jdbcOrderRepository.getOrderCount(location);
    }

    public List<Order> getNewReplyOrder(String location) {
        List<Order> orderList = jdbcOrderRepository.getNewReplyOrder(location);
        for (Order order : orderList) {
            order.setReply(replyService.getReply(order.getId(), "admin"));
        }
        return orderList;
    }
}
