package repository;

import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class JdbcOrderRepository implements OrderRepository {
    private JdbcOperations jdbcOperations;

    private static final String SQL_GET_WAIT = "SELECT * FROM `order` WHERE location = ? && status = 0 ORDER BY updatedon ASC";
    private static final String SQL_GET_WORK = "SELECT  * FROM `order` WHERE location = ? && (status = 1 OR status = 3) ORDER BY  updatedon ASC";
    private static final String SQL_GET_FINISH = "SELECT * FROM `order` WHERE location = ? && status =2 ORDER BY updatedon limit ?,?";
    private static final String SQL_GET_HELPER_ORDER = "SELECT * FROM `order` WHERE phone = ? ORDER BY id DESC";
    private static final String SQL_ADD_ORDER = "INSERT INTO `order` (`updatedon`,`phone`,`email`,`location`,`model`,`os`,`desc`,`name`,`status`) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_MODIFY_ORDER = "UPDATE `order` SET `phone` = ?,`email` = ?,`location` = ?,`model` = ?,`os` = ?,`desc` = ?,`name` = ? WHERE `id` = ?";
    private static final String SQL_GET_ORDER = "SELECT * FROM `order` WHERE id = ?";
    private static final String SQL_DELETE_ORDER = "DELETE FROM `order` WHERE id = ?";
    private static final String SQL_CHANGE_ORDER_STATUS = "UPDATE `order` SET handler = ?,status =? WHERE id = ?";
    private static final String SQL_GET_WAIT_COUNT = "SELECT COUNT(*) FROM `order` WHERE status = 0 AND location = ?";
    private static final String SQL_GET_WORK_COUNT = "SELECT COUNT(*) FROM `order` WHERE status = 1 AND location = ?";
    private static final String SQL_GET_FINISH_COUNT = "SELECT COUNT(*) FROM `order` WHERE status = 2 AND location = ?";

    @Autowired
    public JdbcOrderRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }
    @Autowired
    public JdbcMemberRepository jdbcMemberRepository;

    public List<Order> getWait(String location) {
       return jdbcOperations.query(SQL_GET_WAIT,new OrderRowMapper(),location);
    }

    public List<Order> getWork(String location) {
        return jdbcOperations.query(SQL_GET_WORK,new OrderRowMapper(),location);
    }

    public List<Order> getFinish(String location, int page, int size) {
        return jdbcOperations.query(SQL_GET_FINISH,new OrderRowMapper(),location,page*size,size);
    }

    public List<Order> getSearch(String search, int page, int size) {
        return null;
    }

    public int getSearchNum(String search) {
        return 0;
    }

    public int[] getOrderCount(String location) {
        int waitNum = jdbcOperations.queryForObject(SQL_GET_WAIT_COUNT,Integer.class,location);
        int workNum = jdbcOperations.queryForObject(SQL_GET_WORK_COUNT,Integer.class,location);
        int finishNum = jdbcOperations.queryForObject(SQL_GET_FINISH_COUNT,Integer.class,location);
        return new int[]{waitNum, workNum, finishNum};
    }

    public boolean changeOrderStatus(int oid, int handler, int status) {
        return jdbcOperations.update(SQL_CHANGE_ORDER_STATUS,handler,status,oid)==1;
    }

    public List<Order> getNewReplyOrder(String location) {
        return null;
    }

    public List<Order> getHelperOrder(String phone) {
        return jdbcOperations.query(SQL_GET_HELPER_ORDER,new OrderRowMapper(),phone);
    }

    public boolean addOrder(Order order) {
        int affect = jdbcOperations.update(SQL_ADD_ORDER,order.getUpdatedon(),order.getPhone(),order.getEmail(),
                order.getLocation(),order.getModel(),order.getOs(),order.getDesc(),order.getName(),0);
        return affect==1;
    }

    public Order getOrder(int oid) {
        return jdbcOperations.query(SQL_GET_ORDER,new OrderRowMapper(),oid).get(0);
    }

    public boolean delOrder(int oid) {
        int affect = jdbcOperations.update(SQL_DELETE_ORDER,oid);
        return affect==1;
    }

    public boolean modifyOrder(Order order) {
        int affect = jdbcOperations.update(SQL_MODIFY_ORDER,order.getPhone(),order.getEmail(),
                order.getLocation(),order.getModel(),order.getOs(),order.getDesc(),order.getName(),order.getId());
        return affect==1;
    }

    private static final class OrderRowMapper implements org.springframework.jdbc.core.RowMapper<Order> {
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Order(
                    resultSet.getInt("id"),
                    resultSet.getTimestamp("updatedon"),
                    resultSet.getString("phone"),
                    resultSet.getString("bbsid"),
                    resultSet.getString("email"),
                    resultSet.getString("location"),
                    resultSet.getString("model"),
                    resultSet.getString("os"),
                    resultSet.getString("desc"),
                    resultSet.getInt("handler"),
                    resultSet.getString("name"),
                    resultSet.getInt("status")
            );
        }
    }
}
