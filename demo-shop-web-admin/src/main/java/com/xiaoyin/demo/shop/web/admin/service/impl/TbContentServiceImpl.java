package com.xiaoyin.demo.shop.web.admin.service.impl;

import com.xiaoyin.demo.shop.commons.dto.BaseResult;
import com.xiaoyin.demo.shop.commons.dto.PageInfo;
import com.xiaoyin.demo.shop.domain.TbContent;
import com.xiaoyin.demo.shop.domain.TbUser;
import com.xiaoyin.demo.shop.web.admin.dao.TbContentDao;

import com.xiaoyin.demo.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    TbContentDao tbContentDao;


    @Override
    public TbContent getById(Long id) {
        return tbContentDao.getById(id);
    }

    @Override
    public List<TbContent> selectAll() {

        return tbContentDao.selectAll();
    }

    @Override
    public BaseResult save(TbContent tbContent) {
        BaseResult baseResult =BaseResult.success();
        if (tbContent.getId()==null){
            tbContent.setCreated(new Date());
            tbContent.setUpdated(new Date());
            tbContentDao.insert(tbContent);
        }else {
            tbContent.setUpdated(new Date());
            tbContentDao.update(tbContent);
        }
        return baseResult;
    }

    @Override
    public void delete(Long id) {
        tbContentDao.delete(id);
    }


    /**
     * 批量删除
     * @param idArray
     */
    @Override
    public void deleteMulti(String[] idArray) {
        tbContentDao.deleteMulti(idArray);
    }

    @Override
    public PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent) {

        Map<String,Object> params=new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("tbContent",tbContent);

        PageInfo<TbContent> pageInfo=new PageInfo<>();
        int count = tbContentDao.count(tbContent);
        pageInfo.setDraw(draw);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        pageInfo.setData(tbContentDao.page(params));

        return pageInfo ;
    }

    @Override
    public int count(TbContent tbContent) {
        return tbContentDao.count(tbContent);
    }

    private BaseResult UserCheck(TbUser tbUser){
        BaseResult baseResult=BaseResult.success();


        return baseResult;
    }
}
