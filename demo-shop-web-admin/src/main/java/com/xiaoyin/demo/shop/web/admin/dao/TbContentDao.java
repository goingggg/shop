package com.xiaoyin.demo.shop.web.admin.dao;

import com.xiaoyin.demo.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbContentDao {

    TbContent getById(Long id);

    List<TbContent> selectAll();

    void insert(TbContent tbContent);
    void update(TbContent tbContent);

    void delete(Long id);
    void deleteMulti(String[] idArray);

    List<TbContent> page(Map<String,Object> params);

    int count(TbContent tbContent);
}
