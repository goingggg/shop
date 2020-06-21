package com.xiaoyin.demo.shop.web.admin.service;

import com.xiaoyin.demo.shop.commons.dto.BaseResult;
import com.xiaoyin.demo.shop.commons.dto.PageInfo;
import com.xiaoyin.demo.shop.domain.TbContent;

import java.util.List;

public interface TbContentService {
    TbContent getById(Long id);
    List<TbContent> selectAll();
    BaseResult save(TbContent tbContent);

    void  delete(Long id);
    void deleteMulti(String[] idArray);

    PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent);

    int count(TbContent tbContent);
}
