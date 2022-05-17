package seekgroup.college.community.schedule;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import seekgroup.college.community.cache.HotTagCache;
import seekgroup.college.community.mapper.DiscussionMapper;
import seekgroup.college.community.model.Discussion;

import java.util.*;

/**
 * @author Kyle on 0008 2022/5/8.
 * @version 1.0
 */
@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private DiscussionMapper discussionMapper;

    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000 *300)
    public void hotTagSchedule() {
        int offset = 0;
        int limit = 20;
        log.info("hotTagSchedule start {}", new Date());
        List<Discussion> list = new ArrayList<>();

        Map<String, Integer> priorities = new HashMap<>();
        while (offset == 0 || list.size() == limit) {
            list = discussionMapper.list(offset,limit);
            for (Discussion question : list) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = priorities.get(tag);
                    if (priority != null) {
                        priorities.put(tag, priority + 5 * question.getCommentCount());
                    } else {
                        log.info("Comment Count:" + question);

                        priorities.put(tag, 5 * question.getCommentCount());

                    }
                }

            }
            offset += limit;
        }
        hotTagCache.updateTags(priorities);
        log.info("hotTagSchedule stop {}", new Date());
    }
}
