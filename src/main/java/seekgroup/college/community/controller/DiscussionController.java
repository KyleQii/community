package seekgroup.college.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import seekgroup.college.community.dto.CommentDTO;
import seekgroup.college.community.dto.DiscussionDTO;
import seekgroup.college.community.enums.CommentTypeEnum;
import seekgroup.college.community.service.CommentService;
import seekgroup.college.community.service.DiscussionService;

import java.util.List;

/**
 * @author Kyle on 0012 2022/3/12.
 * @version 1.0
 */
@Controller
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/discussion/{id}")
    public String discussion(@PathVariable(name="id") Long id, Model model){
        DiscussionDTO discussionDTO=discussionService.getById(id);

        //为什么这么写
        List<DiscussionDTO> relatedDiscussions=discussionService.selectRelated(discussionDTO);


        List<CommentDTO> comments=commentService.listByTargetId(id, CommentTypeEnum.DISCUSSION);
        //增加阅读数
        discussionService.incView(id);
        model.addAttribute("discussion",discussionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedDiscussions", relatedDiscussions);

//        System.out.println(discussionDTO);
//        System.out.println(comments);
        return "discussion";
    }
}
