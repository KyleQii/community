package seekgroup.college.community.mapper;

import org.apache.ibatis.annotations.*;
import seekgroup.college.community.model.Discussion;
import seekgroup.college.community.model.DiscussionExample;

import java.util.List;

/**
 * @author Kyle on 0008 2022/5/8.
 * @version 1.0
 */
@Mapper
public interface DiscussionMapper {
    long countByExample(DiscussionExample example);

    int deleteByExample(DiscussionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Discussion record);

    int insertSelective(Discussion record);

    List<Discussion> selectByExample(DiscussionExample example);

    Discussion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Discussion record, @Param("example") DiscussionExample example);

    int updateByExample(@Param("record") Discussion record, @Param("example") DiscussionExample example);

    int updateByPrimaryKeySelective(Discussion record);

    int updateByPrimaryKey(Discussion record);

    @Insert("insert into discussion(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Discussion discussion);

    @Select("select * from discussion order by gmt_create desc limit #{size} offset #{offset}")
    List<Discussion> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(*) from discussion")
    Integer count();

    @Select("select * from discussion where creator=#{userId} limit #{size} offset #{offset}")
    List<Discussion> listByUserId(@Param(value = "userId") Long userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(*) from discussion where creator=#{userId}")
    Integer countByUserId(@Param(value = "userId") Long userId);

    @Select("select * from discussion where id=#{id}")
    Discussion getById(@Param(value = "id") Long id);

    @Update("update discussion set title=#{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id =#{id}")
    void update(Discussion discussion);
}