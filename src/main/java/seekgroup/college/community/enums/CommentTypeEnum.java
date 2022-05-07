package seekgroup.college.community.enums;

/**
 * @author Kyle on 0021 2022/4/21.
 * @version 1.0
 */
public enum CommentTypeEnum {
    DISCUSSION(1),
    COMMENT(2);
    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if(commentTypeEnum.getType().equals(type)){
                return true;
            }
        }
        return false;
    }
}
