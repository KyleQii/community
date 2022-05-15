package seekgroup.college.community.dto;

import lombok.Data;

/**
 * @author Kyle on 0008 2022/5/8.
 * @version 1.0
 */
@Data
public class DiscussionQueryDTO {
    private String search;
    private String sort;
    private Long time;
    private String tag;
    private Integer page;
    private Integer size;
}
