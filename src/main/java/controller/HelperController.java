package controller;

import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.OrderService;
import service.ReplyService;

/**
 * Created by zyzz on 12/28/17.
 */
@Controller
@RequestMapping("/helper")
@SessionAttributes(value = {"phone","order"})

public class HelperController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ReplyService replyService;

    @RequestMapping(value = "/neworder",method = RequestMethod.GET)
    public String newOrder(@ModelAttribute("phone") String phone){
        return "/helper/neworder";
    }

    @RequestMapping(value = "/addorder",method = RequestMethod.POST)
    public String addOrder(Order order,RedirectAttributes model){
        orderService.addOrder(order);
        model.addFlashAttribute("order",orderService.getHelperOrder(order.getPhone()).get(0));
        return "redirect:/helper/now";
    }

    @RequestMapping(value = "/now",method = RequestMethod.GET)
    public String now(@ModelAttribute("order") Order order, Model model){
        return "/helper/now";
    }

    @RequestMapping(value="/history",method = RequestMethod.GET)
    public String history(@ModelAttribute("phone") String phone,Model model){
        model.addAttribute("orderList",orderService.getHelperOrder(phone));
        return "/helper/history";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String showmodify(@ModelAttribute("order") Order order) {
        return "/helper/modify";
    }
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(Order order,RedirectAttributes model) {
        orderService.modifyOrder(order);
        model.addFlashAttribute("order",orderService.getOrder(order.getId()));
        return "redirect:/helper/now";
    }
    @RequestMapping(value = "/deleteorder",method = RequestMethod.GET)
    public String delOrder(@ModelAttribute("order") Order order, RedirectAttributes model, SessionStatus status){
        orderService.delOrder(order.getId());
        model.addFlashAttribute("phone",order.getPhone());
        status.setComplete();
        return "redirect:/helper/neworder";
    }

    @RequestMapping(value = "/addreply", method = RequestMethod.POST)
    public String addReply(String content,@ModelAttribute("order") Order order,RedirectAttributes model) {
        replyService.addHelperReply(order.getId(), content);
        model.addFlashAttribute("order", orderService.getOrder(order.getId()));
        return "redirect:/helper/now";
    }

}
