package seekgroup.college.community.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seekgroup.college.community.dto.CommentDTO;
import seekgroup.college.community.enums.CommentTypeEnum;
import seekgroup.college.community.enums.NotificationTypeEnum;
import seekgroup.college.community.enums.NotificationStatusEnum;
import seekgroup.college.community.exception.CustomizeErrorCode;
import seekgroup.college.community.exception.CustomizeException;
import seekgroup.college.community.mapper.*;
import seekgroup.college.community.model.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kyle on 0021 2022/4/21.
 * @version 1.0
 */

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private DiscussionMapper discussionMapper;

    @Autowired
    private DiscussionExtMapper discussionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    //添加事务
    @Transactional
    public void insert(Comment comment, User commentator) {
        if (StringUtils.isNotBlank(comment.getParentId().toString())) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            // 回复问题
            Discussion discussion = discussionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (discussion == null) {
                throw new CustomizeException(CustomizeErrorCode.DISCUSSION_NOT_FOUND);
            }

            commentMapper.insert(comment);

            //增加评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentExtMapper.incCommentCount(parentComment);

            //创建通知
            createNotify(comment, dbComment.getCommentator(), commentator.getName(), discussion.getTitle(), NotificationTypeEnum.REPLY_COMMENT, discussion.getId());

        } else {
            //回复问题
            Discussion discussion = discussionMapper.selectByPrimaryKey(comment.getParentId());
            if (discussion == null) {
                throw new CustomizeException(CustomizeErrorCode.DISCUSSION_NOT_FOUND);
            }

            comment.setCommentCount(0);
            commentMapper.insert(comment);

            discussion.setCommentCount(1);
            discussionExtMapper.incCommentCount(discussion);

            //创建通知
            createNotify(comment, discussion.getCreator(), commentator.getName(), discussion.getTitle(), NotificationTypeEnum.REPLY_DISCUSSION, discussion.getId());

        }
    }

    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Long outerId) {
        if (receiver.equals(comment.getCommentator())) {
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }

        // 获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList();
        //转换成userid
        userIds.addAll(commentators);

        //获取评论人并转换为 Map  降低时间复杂度
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);

        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));


        //转换comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));

            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
