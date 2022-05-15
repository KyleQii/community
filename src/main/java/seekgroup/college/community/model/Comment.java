package seekgroup.college.community.model;

import lombok.Data;

/**
 * @author Kyle on 0008 2022/5/8.
 * @version 1.0
 */
@Data
public class Comment {
    private Long id;

    private Long parentId;

    private Integer type;

    private Long commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private String content;

    private Integer commentCount;
}