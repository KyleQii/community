package seekgroup.college.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import seekgroup.college.community.dto.CommentCreateDTO;
import seekgroup.college.community.dto.CommentDTO;
import seekgroup.college.community.dto.ResultDTO;
import seekgroup.college.community.enums.CommentTypeEnum;
import seekgroup.college.community.exception.CustomizeErrorCode;
import seekgroup.college.community.model.Comment;
import seekgroup.college.community.model.User;
import seekgroup.college.community.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Kyle on 0021 2022/4/21.
 * @version 1.0
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
//获取二级列表
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,//自动赋值
                       HttpServletRequest request){

        User user = (User)request.getSession().getAttribute("user");
        if(user==null)
        {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment =new Comment();
        comment.setParentId(commentCreateDTO.getParentID());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment,user);
//        System.out.println("Comment:" + comment);
        return ResultDTO.okOf();

    }


    //为什么要用泛型
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id")Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }

}
