package com.xiaoyin.demo.shop.web.admin.service;

import com.xiaoyin.demo.shop.commons.dto.BaseResult;
import com.xiaoyin.demo.shop.commons.dto.PageInfo;
import com.xiaoyin.demo.shop.domain.TbUser;

import java.util.List;

public interface TbUserService {
    TbUser login(String email,String password);
    TbUser getById(Long id);
    List<TbUser> selectAll();
    BaseResult save(TbUser tbUser);

    void  delete(Long id);
    void deleteMulti(String[] idArray);

    PageInfo<TbUser> page(int start, int length,int draw,TbUser tbUser);

    int count(TbUser tbUser);
}
