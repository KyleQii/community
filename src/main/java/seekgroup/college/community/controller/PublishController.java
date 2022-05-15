package seekgroup.college.community.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import seekgroup.college.community.cache.TagCache;
import seekgroup.college.community.dto.DiscussionDTO;
import seekgroup.college.community.model.Discussion;
import seekgroup.college.community.model.User;
import seekgroup.college.community.service.DiscussionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {


    @Autowired
    private DiscussionService discussionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Long id,
                       Model model){
        DiscussionDTO discussion = discussionService.getById(id);
        model.addAttribute("title", discussion.getTitle());
        model.addAttribute("description", discussion.getDescription());
        model.addAttribute("tag", discussion.getTag());
        model.addAttribute("id",discussion.getId());
        model.addAttribute("tags",TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")    //渲染页面
    public String publish(Model model) {
        model.addAttribute("tags",TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")   //执行请求
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id",required = false) Long id,
            HttpServletRequest request,
            Model model //在服务端api接口级别通过model传递到页面
    ) {
        System.out.println("In fun");
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);


//        model.addAttribute("tags", TagCache.get());
        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(description)) {
            model.addAttribute("error", "内容补充不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }


        String invalid = TagCache.filterInvalid(tag);
        System.out.println("In fun2" + invalid);

        if (StringUtils.isNotBlank(invalid)) {
            model.addAttribute("error", "输入非法标签:" + invalid);
            return "publish";
        }




        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }


        Discussion discussion = new Discussion();
        discussion.setTitle(title);
        discussion.setDescription(description);
        discussion.setTag(tag);
        discussion.setCreator(user.getId());
        discussion.setId(id);
        discussionService.createOrUpdate(discussion);

        return "redirect:/";
    }

}