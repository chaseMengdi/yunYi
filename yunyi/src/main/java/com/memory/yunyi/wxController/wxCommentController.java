package com.memory.yunyi.wxController;

import com.memory.yunyi.entity.Comment;
import com.memory.yunyi.entity.VisitInfo;
import com.memory.yunyi.service.CommentService;
import com.memory.yunyi.service.VisitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/")
public class wxCommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private VisitInfoService visitInfoService;


    /**
     * 添加一条评论数据
     *
     * @param comment 评论相关信息
     * @return
     */
    @PostMapping("/addComment")
    public Comment  addComment(@RequestBody Comment comment){
        visitInfoService.incComment(comment.getOwnerID());
        return  commentService.save(comment);
    }


    /**
     * 返回某一用户主页下的评论列表，按时间倒序排列
     * @param comment
     * @return
     */
    @PostMapping("/commentList")
    public List<Comment> commentList(@RequestBody Comment comment){
        return commentService.listByTimeForOne(comment.getOwnerID());
    }
}
