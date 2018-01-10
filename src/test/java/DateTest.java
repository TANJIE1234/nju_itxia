import config.JdbcConfig;
import config.NjuItxiaWebAppInitializer;
import config.RootConfig;
import config.WebConfig;
import entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.JdbcOrderRepository;
import service.OrderService;


import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {NjuItxiaWebAppInitializer.class, RootConfig.class, WebConfig.class, JdbcConfig.class})
public class DateTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private Order order;
    @Autowired
    JdbcOrderRepository jdbcOrderRepository;

    @Test
    public void test(){
        ArrayList<Order> orderArrayList = (ArrayList<Order>) jdbcOrderRepository.getWait("鼓楼");
        Date date = orderArrayList.get(0).getUpdatedon();
        System.out.println(date);
    }


}
