package seekgroup.college.community.dto;

import lombok.Data;
import seekgroup.college.community.model.User;

/**
 * @author Kyle on 0022 2022/4/22.
 * @version 1.0
 */
@Data
public class DiscussionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
