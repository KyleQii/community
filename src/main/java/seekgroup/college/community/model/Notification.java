package seekgroup.college.community.model;

import lombok.Data;

/**
 * 
 * @author Kyle on 0005 2022/5/5.
 * @version 1.0
 */
@Data
public class Notification {
    private Long id;

    private Long notifier;

    private Long receiver;

    /**
    * 通知类型 问题ID 或 回复ID
    */
    private Long outerid;

    /**
    * 评论或回复
    */
    private Integer notificationType;

    private Long gmtCreate;

    private Integer status;

}