package seekgroup.college.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import seekgroup.college.community.dto.NotificationDTO;
import seekgroup.college.community.enums.NotificationTypeEnum;
import seekgroup.college.community.model.User;
import seekgroup.college.community.service.NotificationService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle on 0006 2022/5/6.
 * @version 1.0
 */
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "id") Long id) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);

        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPLY_DISCUSSION.getType() == notificationDTO.getType()) {
            return "redirect:/discussion/" + notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }
    }
}
