package seekgroup.college.community.model;

import lombok.Data;

/**
 * @author Kyle on 0022 2022/4/22.
 * @version 1.0
 */
@Data
public class Discussion {
    private Long id;

    private String title;

    private String description;

    private Long gmtCreate;

    private Long gmtModified;

    private Long creator;

    private Integer commentCount;

    private Integer viewCount;

    private Integer likeCount;

    private String tag;
}