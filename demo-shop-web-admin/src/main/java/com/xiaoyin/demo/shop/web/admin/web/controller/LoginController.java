package com.xiaoyin.demo.shop.web.admin.web.controller;

import com.xiaoyin.demo.shop.domain.TbUser;
import com.xiaoyin.demo.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiaoyin
 * @date 2020/5/20 16:38
 */
@Controller
public class LoginController {
    @Autowired
    TbUserService tbUserService;
    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(){
        return  "login";
    }

    /**
     *
     * @param email
     * @param password
     * @param httpServletRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String email, String password, HttpServletRequest httpServletRequest, Model model){
        TbUser tbUser=tbUserService.login(email,password);
        if (tbUser==null){
            model.addAttribute("message","用户名或密码错误");
            return login();
        }else {
            return  "redirect:/main";
        }
    }
}
