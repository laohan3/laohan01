package com.laohan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laohan.domain.ResponseResult;
import com.laohan.domain.entity.Article;
import com.laohan.domain.vo.ArticleDetailVo;
import com.laohan.domain.vo.ArticleListVo;
import com.laohan.domain.vo.HotArticleVo;
import com.laohan.domain.vo.PageVo;
import com.laohan.domain.entity.Category;
import com.laohan.mapper.ArticleMapper;
import com.laohan.mapper.CategoryMapper;
import com.laohan.service.ArticleService;
import com.laohan.service.CategoryService;
import com.laohan.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryService categoryService;

//    private static final Integer pageSize=5;
        /*
        需要查询浏览量前十的文章,要求展示文章标题和浏览量,能让用户自己点击,跳转到文章详情进行浏览.
        注意:不能把删了的文章展示出来,不能展示草稿.按照浏览亮降序排列.
        */
    @Override
    public ResponseResult getHotArticleList() {
        PageHelper.startPage(1,5);
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getViewCount);
        queryWrapper.eq(Article::getStatus,0);
//        queryWrapper.select(Article::getId,Article::getTitle);
        List<Article> articleList = list(queryWrapper);
//        List<HotArticleVo> voList=new ArrayList<>();
        List<HotArticleVo> hotArticleVoList = BeanCopyUtils.copyBeanList(articleList, HotArticleVo.class);
//        for(Article article:articleList){
//            HotArticleVo vo=new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            voList.add(vo);
//        }
        PageInfo<HotArticleVo> articlePageInfo=new PageInfo<>(hotArticleVoList);
        List<HotArticleVo> resultList = articlePageInfo.getList();
        return ResponseResult.okResult(resultList);

    }

    /*
        首页展示所有文章,选择分类(比如java或者PHP)展示该分类文章,需要分页.做成同一个接口
        认为需要pageNum和categoryId,判断有无categoryId这个参数
         */
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果有categoryId 就要 查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0 ,Article::getCategoryId,categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,0);
        // 对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Article> articles = page.getRecords();
        //查询categoryName
        for(Article article:articles){
            Category category = categoryMapper.selectById(article.getCategoryId());
            if (category!=null){
                article.setCategoryName(category.getName());
            }
        }
        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }




}
