package seekgroup.college.community.dto;

import lombok.Data;
import seekgroup.college.community.model.User;

/**
 * @author Kyle on 0025 2022/4/25.
 * @version 1.0
 */
@Data
public class CommentDTO {
    private Long id;

    private Long parentId;

    private Integer type;

    private Long commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private Integer commentCount;

    private String content;

    private User user;
}
