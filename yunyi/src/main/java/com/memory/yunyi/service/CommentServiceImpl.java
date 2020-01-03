package com.memory.yunyi.service;

import com.memory.yunyi.entity.Comment;
import com.memory.yunyi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Comment save(Comment comment) {
       return commentRepository.save(comment);
    }

    @Override
    public List listByTimeForOne(Integer userID) {
        return commentRepository.queryByOwnerID(userID);
    }
}
