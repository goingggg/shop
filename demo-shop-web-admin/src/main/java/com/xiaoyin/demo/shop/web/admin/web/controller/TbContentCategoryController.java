package com.xiaoyin.demo.shop.web.admin.web.controller;

import com.xiaoyin.demo.shop.domain.TbContentCategory;
import com.xiaoyin.demo.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "content/category")
public class TbContentCategoryController {

    @Autowired
    TbContentCategoryService tbContentCategoryService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbContentCategory> targetList=new ArrayList<>();
        List<TbContentCategory> sourceList = tbContentCategoryService.selectAll();
        sortList(sourceList,targetList,0L);
        model.addAttribute("tbContentCategories", targetList);

        return "content_category_list";

    }

    /**
     * @param sourceList
     * @param targetList
     * @param parentId
     */
    private void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList, Long parentId) {
        for (TbContentCategory tbContentCategory : sourceList) {
            if (tbContentCategory.getParentId().equals(parentId)) {
                targetList.add(tbContentCategory);

                if (tbContentCategory.getIsParent()) {
                    for (TbContentCategory contentCategory : sourceList) {
                        if (contentCategory.getParentId().equals(tbContentCategory.getId())) {
                            sortList(sourceList, targetList, tbContentCategory.getId());
                            break;
                        }

                    }
                }
            }
        }
    }
}
