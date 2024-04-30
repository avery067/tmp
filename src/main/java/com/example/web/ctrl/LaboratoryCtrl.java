package com.example.web.ctrl;

import com.example.web.service.LaboratoryService;
import com.example.web.vo.LaboratoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Avery
 * @create 2024-04-25 9:57
 * @description
 */
@RequestMapping("/laboratory")
@Controller
public class LaboratoryCtrl {
    @Autowired
    private LaboratoryService service;

    @PostMapping("/add")
    public ModelAndView add(String name, Integer max,@RequestParam("file") MultipartFile file){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/laboratory/?pageSize=20&pageNum=1");
        if (service.add(name,max,file)){
            mv.addObject("msg","添加成功");
        }
        return mv;
    }
    @PostMapping("/edit")
    public ModelAndView edit(Integer id,String name,Integer max,@RequestParam("file") MultipartFile file){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/laboratory/?pageSize=20&pageNum=1");
        service.edit(id,name,max,file);
        return mv;
    }

    @GetMapping("/")
    public ModelAndView laboratory(Integer pageSize,Integer pageNum,String name){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("laboratory");
        mv.addObject("laboratories", service.laboratory(pageSize,pageNum,name));
        return mv;
    }
    @GetMapping("/delete")
    public ModelAndView delete(Integer id){
        ModelAndView mv = new ModelAndView();
        service.delete(id);
        mv.setViewName("redirect:/laboratory/?pageSize=20&pageNum=1");
        return mv;
    }

}
