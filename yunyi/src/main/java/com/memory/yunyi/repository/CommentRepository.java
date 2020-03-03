package com.memory.yunyi.repository;

import com.memory.yunyi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Integer> {

    //    查询某一用户收到的评论列表，按时间倒序排列
    //@Query("select a from Comment a where a.ownerID = ?1 order by a.cmtID DESC ")
    @Query(value = "SELECT a.cmtid,a.commenterid,a.ownerid,a.text,a.time, b.nick_name AS commenter_name,b.avatar_url AS avatar_url  \n" +
            "FROM comment a ,user b\n" +
            "WHERE a.commenterid=b.user_id \n" +
            "AND a.ownerid=?1 ORDER BY a.cmtid DESC",nativeQuery = true)
    List<Comment> queryByOwnerID(String userID);
}
