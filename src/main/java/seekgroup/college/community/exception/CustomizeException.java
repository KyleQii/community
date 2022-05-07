package seekgroup.college.community.exception;

/**
 * @author Kyle on 0020 2022/4/20.
 * @version 1.0
 */
public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;
    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
