package com.example.web.ctrl;

import com.example.web.mapper.LaboratoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Avery
 * @create 2024-04-25 15:07
 * @description
 */
@Controller
public class MenuCtrl {


    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/logout")
    public String logout(){
        return "index";
    }
    @GetMapping("/main")
    public String main(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");


        return "main";
    }

}
