package seekgroup.college.community.dto;

import lombok.Data;

/**
 * @author Kyle on 0021 2022/4/21.
 * @version 1.0
 */
@Data
public class CommentCreateDTO {
    private Long parentID;
    private String content;
    private Integer type;
}
