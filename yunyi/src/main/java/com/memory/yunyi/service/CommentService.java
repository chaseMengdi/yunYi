package com.memory.yunyi.service;

import com.memory.yunyi.entity.Comment;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CommentService {
    Comment save(Comment comment);
    List<Comment>  listByTimeForOne(Integer userID);
}
