package seekgroup.college.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import seekgroup.college.community.model.Notification;
import seekgroup.college.community.model.NotificationExample;

import java.util.List;

/**
 * 
 * @author Kyle on 0005 2022/5/5.
 * @version 1.0
 */
@Mapper
public interface NotificationMapper {
    long countByExample(NotificationExample example);

    int deleteByExample(NotificationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Notification record);

    int insertSelective(Notification record);

    List<Notification> selectByExample(NotificationExample example);

    Notification selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Notification record, @Param("example") NotificationExample example);

    int updateByExample(@Param("record") Notification record, @Param("example") NotificationExample example);

    int updateByPrimaryKeySelective(Notification record);

    int updateByPrimaryKey(Notification record);

    @Select("select * from NOTIFICATION order by gmt_create desc limit #{size} offset #{offset}")
    List<Notification> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
}