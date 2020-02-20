package com.memory.yunyi.repository;

import com.memory.yunyi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Integer> {

    //    查询某一用户收到的评论列表，按时间倒序排列
    @Query("select a from Comment a where a.ownerID = ?1 order by a.cmtID DESC ")
    List<Comment> queryByOwnerID(String userID);
}
