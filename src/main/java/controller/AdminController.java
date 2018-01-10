package controller;

import entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.MemberService;
import service.OrderService;
import service.ReplyService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
@SessionAttributes(value = {"checkedMember"})
public class AdminController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ReplyService replyService;

    private static final String MESSAGE_INCORRECT_USERNAME_OR_PASSWORD = "用户名或密码不正确！";

    @RequestMapping(value = "/wait", method = RequestMethod.GET)
    public String showWait(@ModelAttribute("checkedMember") Member member, Model model) {
        model.addAttribute("waitList", orderService.getWait(member.getLocation()));
        model.addAttribute("number", orderService.getOrderCount(member.getLocation()));
        return "/admin/wait";
    }

    @RequestMapping(value = "/work", method = RequestMethod.GET)
    public String showWork(Model model) {
        model.addAttribute("workList", orderService.getWork());
        return "/admin/work";
    }

    @RequestMapping(value = "/finish", method = RequestMethod.GET)
    public String showFinish(Model model) {
        model.addAttribute("finishList", orderService.getFinish());
        return "/admin/finish";
    }

    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    public String adminLogin(Member member, RedirectAttributes redirectModel) {
        List<Member> checkedMember = memberService.adminLogin(member);
        if (checkedMember.size() != 0) {
            redirectModel.addFlashAttribute("checkedMember", checkedMember.get(0));
            return "redirect:/admin/wait";
        } else {
            redirectModel.addFlashAttribute("message", MESSAGE_INCORRECT_USERNAME_OR_PASSWORD);
            return "redirect:/adminlogin";
        }
    }

    @RequestMapping(value = "/ordertowork", method = RequestMethod.GET)
    public String changeOrderToWork(@RequestParam("orderid") int orderId, @ModelAttribute("checkedMember") Member member) {
        if (orderService.changeToWork(orderId, member.getId()))
            return "redirect:/admin/wait";
        return "error";
    }

    @RequestMapping(value = "/ordertowait", method = RequestMethod.GET)
    public String changeOrderToWait(@RequestParam("orderid") int orderId, @ModelAttribute("checkedMember") Member member) {
        if (orderService.changeToWait(orderId, member.getId()))
            return "redirect:/admin/wait";
        return "error";
    }

    @RequestMapping(value = "/ordertofinish", method = RequestMethod.GET)
    public String changeOrderToFinish(@RequestParam("orderid") int orderId, @ModelAttribute("checkedMember") Member member) {
        if (orderService.changeToFinish(orderId, member.getId()))
            return "redirect:/admin/wait";
        return "error";
    }

    @RequestMapping(value = "/addreply", method = RequestMethod.POST)
    public String addReply(HttpServletRequest request, @ModelAttribute("checkedMember") Member member) {
        int orderId = Integer.parseInt(request.getParameter("order"));
        String content = request.getParameter("content");
        replyService.addAdminReply(orderId, member.getId(),content);
        return "redirect:/admin/wait";
    }
}
