package com.memory.yunyi.serviceImpl;

import com.memory.yunyi.entity.Comment;
import com.memory.yunyi.repository.CommentRepository;
import com.memory.yunyi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List listByTimeForOne(String userID) {
        return commentRepository.queryByOwnerID(userID);
    }
}
