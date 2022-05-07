package seekgroup.college.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import seekgroup.college.community.model.Discussion;

import java.util.List;

/**
 * @author Kyle on 0002 2022/4/2.
 * @version 1.0
 */
@Mapper
public interface DiscussionExtMapper {


    int incView(Discussion record);
    int incCommentCount(Discussion record);
    List<Discussion> selectRelated (Discussion discussion);

}