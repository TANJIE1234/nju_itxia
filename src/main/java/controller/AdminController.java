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
        model.addAttribute("member", member);
        return "/admin/wait";
    }

    @RequestMapping(value = "/work", method = RequestMethod.GET)
    public String showWork(@ModelAttribute("checkedMember") Member member,Model model) {
        model.addAttribute("workList", orderService.getWork(member.getLocation()));
        model.addAttribute("number", orderService.getOrderCount(member.getLocation()));
        model.addAttribute("member", member);
        return "/admin/work";
    }

    @RequestMapping(value = "/finish", method = RequestMethod.GET)
    public String showFinish(@RequestParam(name = "page",defaultValue = "0") String reqPage,@ModelAttribute("checkedMember") Member member,Model model) {
        int[] number = orderService.getOrderCount(member.getLocation());
        int finishNum = number[2];
        int maxPage = (finishNum-1) / 1;
        int page = Integer.parseInt(reqPage);
        int prePage = (page==0?0:page-1);
        int nextPage = (page==maxPage?maxPage:page+1);
        String nowPage = page + 1 + "";
        model.addAttribute("finishList", orderService.getFinish(member.getLocation(),page));
        model.addAttribute("number", number);
        model.addAttribute("member", member);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("prePage",prePage);
        model.addAttribute("nextPage",nextPage);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("page", page);
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
        String referer = request.getHeader("referer");
        String content = request.getParameter("content");
        replyService.addAdminReply(orderId, member.getId(), content);
        //return "redirect:/admin/wait";
        return "redirect:"+referer;
    }

    @RequestMapping(value = "/delreply", method = RequestMethod.POST)
    public String deleteReply(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        int replyId = Integer.parseInt(request.getParameter("id"));
        replyService.delReply(replyId);
        return "redirect:"+referer;
    }
}
