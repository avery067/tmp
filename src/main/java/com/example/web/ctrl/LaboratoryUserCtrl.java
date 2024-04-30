package com.example.web.ctrl;

import com.example.web.service.LaboratoryService;
import com.example.web.service.LaboratoryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Avery
 * @create 2024-04-25 11:59
 * @description
 */
@Controller
@RequestMapping("/laboratory/user")
public class LaboratoryUserCtrl {
    @Autowired
    private LaboratoryService service;
    @Autowired
    private LaboratoryUserService userService;
    @GetMapping("/")
    public ModelAndView laboratoryUser(Integer pageSize,Integer pageNum){
        ModelAndView view = new ModelAndView();
        view.addObject("list",service.laboratoryUser(pageSize,pageNum));
        view.setViewName("laboratoryUser");
        return view;
    }
    @GetMapping("/audit")
    public ModelAndView audit(Integer id,Integer state){
        ModelAndView view = new ModelAndView();
        userService.audti(id,state);
        view.setViewName("redirect:/laboratory/user/?pageSize=20&pageNum=1");
        return view;
    }
}
