package controller;

import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.MemberService;
import service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by zyzz on 11/24/17.
 */
@Controller
//@SessionAttributes("checkedMember")
public class LoginController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = {"/", "login"}, method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = {"/adminlogin"}, method = RequestMethod.GET)
    public String adminLoginPage() {
        return "/admin/login";
    }

    @RequestMapping(value = {"/aboutus"}, method = RequestMethod.GET)
    public String aboutUs() {
        return "aboutus";
    }

    @RequestMapping(value = {"/donation"}, method = RequestMethod.GET)
    public String donation() {
        return "donation";
    }

    @RequestMapping(value = {"/helperlogin"}, method = RequestMethod.POST)
    public String helperLogin(String phone, RedirectAttributes model) {
        List<Order> orderList= orderService.getHelperOrder(phone);
        model.addFlashAttribute("phone",phone);
        if (orderList.isEmpty()){
            return "redirect:/helper/neworder";
        }
        else {
            model.addFlashAttribute("order",orderList.get(0));
            return "redirect:/helper/now";
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        //session.setComplete();
        return "redirect:/";
    }
}
