package com.xiaoyin.demo.shop.web.admin.dao;

import com.xiaoyin.demo.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao {
    List<TbContentCategory> selectAll();

}
