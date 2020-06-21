package com.xiaoyin.demo.shop.web.admin.service.impl;

import com.xiaoyin.demo.shop.commons.dto.BaseResult;
import com.xiaoyin.demo.shop.commons.dto.PageInfo;
import com.xiaoyin.demo.shop.commons.utils.RegexpUtils;
import com.xiaoyin.demo.shop.domain.TbUser;
import com.xiaoyin.demo.shop.web.admin.dao.TbUserDao;
import com.xiaoyin.demo.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    TbUserDao tbUserDao;
    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser=tbUserDao.getByEmail(email);
        if (tbUser!=null&&password!=null){
            String md5Password= DigestUtils.md5DigestAsHex(password.getBytes());
            if (md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }
        }
        return null;
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public List<TbUser> selectAll() {

        return tbUserDao.selectAll();
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = UserCheck(tbUser);
        if (baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            tbUser.setUpdated(new Date());
            //新增
            if (tbUser.getId()==null){
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);
                baseResult=BaseResult.success("新增成功");
            }else {
                tbUserDao.update(tbUser);
                baseResult=BaseResult.success("修改成功");
            }
        }
        return baseResult;
    }

    @Override
    public void delete(Long id) {
        tbUserDao.delete(id);
    }


    /**
     * 批量删除
     * @param idArray
     */
    @Override
    public void deleteMulti(String[] idArray) {
        tbUserDao.deleteMulti(idArray);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length,int draw,TbUser tbUser) {

        Map<String,Object> params=new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("tbUser",tbUser);

        PageInfo<TbUser> pageInfo=new PageInfo<>();
        int count = tbUserDao.count(tbUser);
        pageInfo.setDraw(draw);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        pageInfo.setData(tbUserDao.page(params));

        return pageInfo ;
    }

    @Override
    public int count(TbUser tbUser) {
        return tbUserDao.count(tbUser);
    }

    private BaseResult UserCheck(TbUser tbUser){
        BaseResult baseResult=BaseResult.success();

         if (!RegexpUtils.checkEmail(tbUser.getEmail())){
            baseResult=BaseResult.fail("邮箱格式错误");
        }
         //
         else if (StringUtils.isBlank(tbUser.getPassword())){
             baseResult=BaseResult.fail("密码不能为空");
         }
       else if  (StringUtils.isBlank(tbUser.getUsername())){
            baseResult=BaseResult.fail("用户名不能为空");
        }


        else if (!RegexpUtils.checkPhone(tbUser.getPhone())){
            baseResult=BaseResult.fail("手机格式错误");
        }
        return baseResult;
    }
}
