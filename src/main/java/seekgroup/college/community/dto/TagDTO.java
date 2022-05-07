package seekgroup.college.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Kyle on 0004 2022/5/4.
 * @version 1.0
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
