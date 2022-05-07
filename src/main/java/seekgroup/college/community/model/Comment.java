package seekgroup.college.community.model;

import lombok.Data;

/**
 * @author Kyle on 0029 2022/4/29.
 * @version 1.0
 */
@Data
public class Comment {
    private Long id;

    /**
     * 父类ID
     */
    private Long parentId;

    /**
     * 父类类型
     */
    private Integer type;

    private Long commentator;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 更新时间
     */
    private Long gmtModified;

    private Long likeCount;

    private String content;

    private Integer commentCount;
}