package com.laohan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laohan.domain.ResponseResult;
import com.laohan.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult getHotArticleList();

    ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
