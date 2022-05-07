package seekgroup.college.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import seekgroup.college.community.model.Comment;

/**
 * @author Kyle on 0029 2022/4/29.
 * @version 1.0
 */
@Mapper
public interface CommentExtMapper {

    int incCommentCount(Comment comment);
}