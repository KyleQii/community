package seekgroup.college.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import seekgroup.college.community.dto.PaginationDTO;
import seekgroup.college.community.model.User;
import seekgroup.college.community.service.DiscussionService;
import seekgroup.college.community.service.NotificationService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle on 0011 2022/3/11.
 * @version 1.0
 */
@Controller
public class profileController {


    @Autowired
    private DiscussionService discussionService;

    @Autowired
    private NotificationService notificationService;


    @GetMapping("/profile/{action}")
    public String profile(
            HttpServletRequest request,
            @PathVariable(name="action")String action,
            Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "5") Integer size){

        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }

        if("account".contains(action)) {
            model.addAttribute("section", "account");
            model.addAttribute("sectionName", "我的个人空间");
        }
        else if("discussions".equals(action)){
            model.addAttribute("section","discussions");
            model.addAttribute("sectionName","我的问题");
            PaginationDTO paginationDTO = discussionService.list(user.getId(), page, size);
            model.addAttribute("pagination",paginationDTO);
        }else if("replies".equals(action)){
            PaginationDTO paginationDTO = notificationService.list(user.getId(), page, size);
            model.addAttribute("section", "replies");
            model.addAttribute("pagination", paginationDTO);
            model.addAttribute("sectionName", "最新回复");
        }
        return "profile";
    }
}
