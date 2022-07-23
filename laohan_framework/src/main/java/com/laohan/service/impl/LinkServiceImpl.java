package com.laohan.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laohan.domain.ResponseResult;
import com.laohan.domain.entity.Link;
import com.laohan.domain.vo.LinkVo;
import com.laohan.mapper.LinkMapper;
import com.laohan.service.LinkService;
import com.laohan.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper,Link> implements LinkService{
    @Resource
    private LinkMapper linkMapper;

    @Override
    public ResponseResult getAllLink() {
        //封装条件,查询数据库
        LambdaQueryWrapper<Link> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus,"0");
        List<Link> linkList = linkMapper.selectList(queryWrapper);
        //转换vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(linkList, LinkVo.class);
        //返回
        return ResponseResult.okResult(linkVos);
    }
}
