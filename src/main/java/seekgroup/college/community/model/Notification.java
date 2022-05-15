package seekgroup.college.community.model;

import lombok.Data;

/**
 * @author Kyle on 0008 2022/5/8.
 * @version 1.0
 */
@Data
public class Notification {
    private Long id;

    private Long notifier;

    private Long receiver;

    private Long outerid;

    private Integer type;

    private Long gmtCreate;

    private Integer status;

    private String notifierName;

    private String outerTitle;
}