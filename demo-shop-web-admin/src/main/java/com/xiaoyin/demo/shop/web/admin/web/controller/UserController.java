package com.xiaoyin.demo.shop.web.admin.web.controller;

import com.xiaoyin.demo.shop.commons.dto.BaseResult;
import com.xiaoyin.demo.shop.commons.dto.PageInfo;
import com.xiaoyin.demo.shop.domain.TbUser;
import com.xiaoyin.demo.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoyin
 * @date 2020/5/21 11:22
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    TbUserService tbUserService;

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser=null;
        if (id!=null){
            tbUser=tbUserService.getById(id);
        }else {
            tbUser=new TbUser();
        }
        return tbUser;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("tbUsers",tbUserService.selectAll());
        return "user_list";
    }
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes,Model model){
        BaseResult baseResult = tbUserService.save(tbUser);

        if (baseResult.getStatus()==200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        else {
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }

    }

    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult=null;
        if (StringUtils.isNoneBlank(ids)){
            String[] idArray = ids.split(",");
            tbUserService.deleteMulti(idArray);
            baseResult=BaseResult.success("删除成功");
        }
        else {
            baseResult=BaseResult.fail("删除失败");
        }
        return baseResult;
    }
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest httpServletRequest,TbUser tbUser){
        String strDraw = httpServletRequest.getParameter("draw");
        String strStart = httpServletRequest.getParameter("start");
        String strLength = httpServletRequest.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        PageInfo<TbUser> pageInfo = tbUserService.page(start, length,draw,tbUser);

        return pageInfo;
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbUser tbUser){

        return "user_detail";
    }

}
