package com.xiaoyin.demo.shop.web.admin.dao;

import com.xiaoyin.demo.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao {

    TbUser getByEmail(String email);
    TbUser getById(Long id);

    List<TbUser> selectAll();

    void insert(TbUser tbUser);
    void update(TbUser tbUser);

    void delete(Long id);
    void deleteMulti(String[] idArray);

    List<TbUser> page(Map<String,Object> params);

    int count(TbUser tbUser);

}
