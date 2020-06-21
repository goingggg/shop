package com.xiaoyin.demo.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> upload(MultipartFile dropFile, HttpServletRequest request){
        Map<String,Object> result=new HashMap<>();
        //获取名字
        String originalFilename = dropFile.getOriginalFilename();

        //获取路径
        String realPath = request.getSession().getServletContext().getRealPath("static/upload");
        File file=new File(realPath);
        if (!file.exists()){
            file.mkdir();
        }
        file=new File(realPath,originalFilename);
        try {
            dropFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
