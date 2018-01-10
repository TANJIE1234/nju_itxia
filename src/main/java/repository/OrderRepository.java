package repository;

import entity.Order;
import java.util.List;

public interface OrderRepository {
    List<Order> getWait(String location);

    List<Order> getWork(String location);

    List<Order> getFinish(String location, int page, int size);

    List<Order> getSearch(String search, int page, int size);

    int getSearchNum(String search);

    int[] getOrderCount(String location);

    boolean changeOrderStatus(int oid, int handler, int status);

    List<Order> getNewReplyOrder(String location);

    List<Order> getHelperOrder(String account);

    boolean addOrder(Order order);

    Order getOrder(int oid);

    boolean delOrder(int oid);

    boolean modifyOrder(Order order);
}
