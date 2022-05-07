package seekgroup.college.community.enums;

/**
 * @author Kyle on 0005 2022/5/5.
 * @version 1.0
 */

public enum NotificationTypeEnum {
    REPLY_DISCUSSION(1,"回复问题"),
    REPLY_COMMENT(2,"回复评论");
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }
    public static String nameOfType(int type) {
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if (notificationTypeEnum.getType() == type) {
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
