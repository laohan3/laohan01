package com.laohan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laohan.domain.ResponseResult;
import com.laohan.domain.entity.Comment;

public interface CommentService extends IService<Comment> {
    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
