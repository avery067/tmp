package com.example.web.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.web.entity.SysUser;
import com.example.web.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Avery
 * @create 2024-04-25 9:56
 * @description
 */
@RequestMapping("/sys/user")
@Controller
public class SysUserCtrl {
    @Autowired
    private SysUserService sysUserService;
    @PostMapping("/login")
    public ModelAndView login(String sysUserName, String sysPassWord){
        return sysUserService.login(sysUserName,sysPassWord);
    }
    @GetMapping("/logout")
    public ModelAndView logout(){
        return new ModelAndView("index");
    }
    @GetMapping("/main")
    public ModelAndView index(){
        return new ModelAndView("main");
    }
    @PostMapping("/add")
    public ModelAndView add(String sysUserName,String sysPassWord){
        return sysUserService.add(sysUserName,sysPassWord);
    }
    @GetMapping("/delete")
    public ModelAndView delete(Integer id){
        sysUserService.removeById(id);
        return new ModelAndView("redirect:/sys/user/?pageSize=20&pageNum=1");
    }
    @PostMapping("/edit")
    public ModelAndView edit(Integer id,String sysUserName,String sysUserPassWord){
        return sysUserService.edit(id,sysUserName,sysUserPassWord);
    }
    @GetMapping("/")
    public ModelAndView user(Integer pageSize,Integer pageNum,String name){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("sysUser");
        if (StringUtils.isEmpty(name)){
            mv.addObject("users",sysUserService.page(new Page<>(pageNum,pageSize)));
        }else{
            mv.addObject("users",sysUserService.page(new Page<>(pageNum,pageSize),new LambdaQueryWrapper<SysUser>().like(SysUser::getSysUserName,name)));
        }

        return mv;
    }
}
