package com.example.web.ctrl;

import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Avery
 * @create 2024-04-25 9:56
 * @description
 */
@Controller
public class UserCtrl {
    @Autowired private UserService service;
    @GetMapping("/user")
    public ModelAndView user(Integer pageSize,Integer pageNum,String name){
        ModelAndView view = new ModelAndView("user");
        view.addObject("users",service.user(pageSize,pageNum,name));
        return view;
    }
    @GetMapping("/user/add")
    public ModelAndView add(String userName,String passWord){
        return service.add(userName,passWord);
    }
    @PostMapping("/user/edit")
    public ModelAndView edit(Integer id,String userName,String passWord,String mobile,String no){
        return service.edit(id,userName,passWord,mobile,no);
    }
    @GetMapping("/user/delete")
    public ModelAndView delete(Integer id){
        return service.delete(id);
    }
}
