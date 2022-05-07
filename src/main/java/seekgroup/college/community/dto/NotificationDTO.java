package seekgroup.college.community.dto;

import lombok.Data;

/**
 * @author Kyle on 0006 2022/5/6.
 * @version 1.0
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
