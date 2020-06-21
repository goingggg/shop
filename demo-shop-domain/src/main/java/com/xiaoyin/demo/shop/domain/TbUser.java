package com.xiaoyin.demo.shop.domain;


import com.xiaoyin.demo.shop.commons.persistence.BaseEntity;
import org.hibernate.validator.constraints.Length;

public class TbUser extends BaseEntity {
    @Length(min = 2,max = 10,message = "用户名长度必须4-10之间")
    private String username;
    private String password;

    private String phone;
    private String email;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}