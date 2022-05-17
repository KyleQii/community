package seekgroup.college.community.model;

import lombok.Data;

/**
 * @author Kyle on 0008 2022/5/8.
 * @version 1.0
 */
@Data
public class Notification {
    private Long id;

    //发出通知的人
    private Long notifier;
    //接收者
    private Long receiver;
    //问题id或评论id  外键 parentId -commentId
    private Long outerid;
    //评论或回复
    private Integer type;

    private Long gmtCreate;

    private Integer status;

    private String notifierName;

    private String outerTitle;
}