package com.xiaoyin.demo.shop.web.admin.service.impl;

import com.xiaoyin.demo.shop.domain.TbContentCategory;
import com.xiaoyin.demo.shop.web.admin.dao.TbContentCategoryDao;
import com.xiaoyin.demo.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired
    TbContentCategoryDao tbContentCategoryDao;
    @Override
    public List<TbContentCategory> selectAll(){
        List<TbContentCategory> tbContentCategories = tbContentCategoryDao.selectAll();
        return tbContentCategories;
    }
}
