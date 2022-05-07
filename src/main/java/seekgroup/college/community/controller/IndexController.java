package seekgroup.college.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import seekgroup.college.community.dto.PaginationDTO;
import seekgroup.college.community.service.DiscussionService;

@Controller
public class IndexController { //获取用户cookie


    @Autowired
    private DiscussionService discussionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size) {

        PaginationDTO pagination=discussionService.list(page,size);
        model.addAttribute("pagination",pagination);
        System.out.println("User:" + model);
        return "index";
    }
}



