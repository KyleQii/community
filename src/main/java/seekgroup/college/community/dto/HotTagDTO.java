package seekgroup.college.community.dto;

import lombok.Data;

/**
 * @author Kyle on 0006 2022/5/6.
 * @version 1.0
 */
@Data
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;

    //
    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
