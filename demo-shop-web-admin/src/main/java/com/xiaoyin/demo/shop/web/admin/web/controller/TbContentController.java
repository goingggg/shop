package com.xiaoyin.demo.shop.web.admin.web.controller;

import com.xiaoyin.demo.shop.commons.dto.BaseResult;
import com.xiaoyin.demo.shop.commons.dto.PageInfo;
import com.xiaoyin.demo.shop.domain.TbContent;
import com.xiaoyin.demo.shop.web.admin.service.TbContentService;
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

@Controller
@RequestMapping(value = "content")
public class TbContentController {
    @Autowired
    TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent=null;
        if (id!=null){
            tbContent=tbContentService.getById(id);
        }else {
            tbContent=new TbContent();
        }
        return tbContent;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        //model.addAttribute("tbContents",tbContentService.selectAll());
        return "content_list";
    }
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "content_form";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes, Model model){
        BaseResult baseResult = tbContentService.save(tbContent);

        if (baseResult.getStatus()==200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }
        else {
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }

    }

    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult=null;
        if (StringUtils.isNoneBlank(ids)){
            String[] idArray = ids.split(",");
            tbContentService.deleteMulti(idArray);
            baseResult=BaseResult.success("删除成功");
        }
        else {
            baseResult= BaseResult.fail("删除失败");
        }
        return baseResult;
    }
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbContent> page(HttpServletRequest httpServletRequest, TbContent tbContent){
        String strDraw = httpServletRequest.getParameter("draw");
        String strStart = httpServletRequest.getParameter("start");
        String strLength = httpServletRequest.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        PageInfo<TbContent> pageInfo = tbContentService.page(start, length,draw,tbContent);

        return pageInfo;
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbContent tbContent){

        return "content_detail";
    }
}
