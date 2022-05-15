package seekgroup.college.community.service;


import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seekgroup.college.community.dto.DiscussionDTO;
import seekgroup.college.community.dto.DiscussionQueryDTO;
import seekgroup.college.community.dto.PaginationDTO;
import seekgroup.college.community.exception.CustomizeErrorCode;
import seekgroup.college.community.exception.CustomizeException;
import seekgroup.college.community.mapper.DiscussionExtMapper;
import seekgroup.college.community.mapper.DiscussionMapper;
import seekgroup.college.community.mapper.UserMapper;
import seekgroup.college.community.model.Discussion;
import seekgroup.college.community.model.DiscussionExample;
import seekgroup.college.community.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kyle on 0019 2022/2/19.
 * @version 1.0
 */
@Service
@Slf4j
public class DiscussionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussionExtMapper discussionExtMapper;

    @Autowired
    private DiscussionMapper discussionMapper;

    public PaginationDTO list(String search, String tag, String sort,Integer page, Integer size) {

        if (StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");
            search = Arrays
                    .stream(tags)
                    .filter(StringUtils::isNotBlank)
                    .map(t -> t.replace("+", "").replace("*", "").replace("?", ""))
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.joining("|"));
        }


        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;


        DiscussionQueryDTO discussionQueryDTO = new DiscussionQueryDTO();
        discussionQueryDTO.setSearch(search);
        if (StringUtils.isNotBlank(tag)) {
            tag = tag.replace("+", "").replace("*", "").replace("?", "");
            discussionQueryDTO.setTag(tag);
        }


        Integer totalCount = discussionExtMapper.countBySearch(discussionQueryDTO);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }

        paginationDTO.setPagination(totalPage,page);
        Integer offset = page < 1 ? 0 : size * (page - 1);
        discussionQueryDTO.setSize(size);
        discussionQueryDTO.setPage(offset);
        List<Discussion> discussions=discussionExtMapper.selectBySearch(discussionQueryDTO);

        PageHelper.startPage(offset,size);
        List<DiscussionDTO> discussionDTOList=new ArrayList<>();
        //User user;

        User user=userMapper.selectByPrimaryKey(new Long(1578));
        for (Discussion discussion : discussions) {
            user=userMapper.selectByPrimaryKey(discussion.getCreator());
//            System.out.println("User:" + discussion.getCreator() + user);
            DiscussionDTO discussionDTO = new DiscussionDTO();
            BeanUtils.copyProperties(discussion,discussionDTO);
            //为什么user==null
            discussionDTO.setUser(user);
            discussionDTOList.add(discussionDTO);

        }
        paginationDTO.setData(discussionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = discussionMapper.countByUserId(userId);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }

        paginationDTO.setPagination(totalPage,page);
        Integer offset=size*(page-1);
        List<Discussion> discussions=discussionMapper.listByUserId(userId,offset,size);
        List<DiscussionDTO> discussionDTOList=new ArrayList<>();

        for (Discussion discussion : discussions) {
            User user=userMapper.selectByPrimaryKey(discussion.getCreator());
            DiscussionDTO discussionDTO = new DiscussionDTO();
            BeanUtils.copyProperties(discussion,discussionDTO);
            discussionDTO.setUser(user);
            discussionDTOList.add(discussionDTO);
        }
        paginationDTO.setData(discussionDTOList);
        return paginationDTO;
    }

    public DiscussionDTO getById(Long id) {
        Discussion discussion=discussionMapper.selectByPrimaryKey(id);
        if(discussion==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        DiscussionDTO discussionDTO = new DiscussionDTO();
        BeanUtils.copyProperties(discussion,discussionDTO);
        User user=userMapper.selectByPrimaryKey(discussion.getCreator());
        discussionDTO.setUser(user);
        return discussionDTO;
    }

    public void createOrUpdate(Discussion discussion) {
        if(discussion.getId()==null){
            //创建
            discussion.setGmtCreate(System.currentTimeMillis());
            discussion.setGmtModified(discussion.getGmtCreate());
            discussion.setViewCount(0);
            discussion.setLikeCount(0);
            discussion.setCommentCount(0);
            discussionMapper.insert(discussion);
        }else {
            Discussion updateDiscussion = new Discussion();
            updateDiscussion.setGmtModified(System.currentTimeMillis());
            updateDiscussion.setTitle(discussion.getTitle());
            updateDiscussion.setDescription(discussion.getDescription());
            updateDiscussion.setTag(discussion.getTag());
            DiscussionExample example = new DiscussionExample();
            example.createCriteria()
                    .andIdEqualTo(discussion.getId());
            int updated=discussionMapper.updateByExampleSelective(updateDiscussion, example);
            if(updated!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }


    public void incView(Long id) {
        Discussion discussion = new Discussion();
        discussion.setId(id);
        //为什么设置viewcount为1
        discussion.setViewCount(1);
        discussionExtMapper.incView(discussion);
    }

    public List<DiscussionDTO> selectRelated(DiscussionDTO queryDTO) {

        if(StringUtils.isBlank(queryDTO.getTag())){
            return new ArrayList<>();
        }
        log.info("selectRelated queryDTO:" + queryDTO.toString());
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        log.info("selectRelated tags:" + tags.toString());
        String regexpTag = Arrays
                .stream(tags)
                .filter(StringUtils::isNotBlank)
                .map(t -> t.replace("+", "").replace("*", "").replace("?", ""))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining("|"));
        log.info("selectRelated regexpTag:" + regexpTag.toString());

        Discussion discussion = new Discussion();
        discussion.setId(queryDTO.getId());
        discussion.setTag(regexpTag);

        List<Discussion> discussions = discussionExtMapper.selectRelated(discussion);
        List<DiscussionDTO> discussionDTOS = discussions.stream().map(q -> {
            DiscussionDTO discussionDTO = new DiscussionDTO();
            BeanUtils.copyProperties(q, discussionDTO);
            return discussionDTO;
        }).collect(Collectors.toList());

        log.info("selectRelated discussionDTOS:" + discussionDTOS.toString());

        return discussionDTOS;
    }
}
