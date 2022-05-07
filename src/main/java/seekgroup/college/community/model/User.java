package seekgroup.college.community.model;

import lombok.Data;

/**
 * @author Kyle on 0022 2022/4/22.
 * @version 1.0
 */
@Data
public class User {
    private Long id;

    private String accountId;

    private String name;

    private String token;

    private Long gmtCreate;

    private Long gmtModified;

    private String bio;

    private String avatarUrl;
}