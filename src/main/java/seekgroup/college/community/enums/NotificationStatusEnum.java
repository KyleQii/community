package seekgroup.college.community.enums;

/**
 * @author Kyle on 0005 2022/5/5.
 * @version 1.0
 */
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1)
    ;
    private int status;

    public int getStatus() {
        return status;
    }
    NotificationStatusEnum(int status) {
        this.status = status;
    }


}
