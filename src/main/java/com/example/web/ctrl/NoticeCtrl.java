package com.example.web.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.web.entity.Notice;
import com.example.web.service.NoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Avery
 * @create 2024-04-25 9:56
 * @description
 */
@RequestMapping("/notice")
@Controller
public class NoticeCtrl {
    @Autowired
    private NoticeService noticeService;
    @PostMapping("/add")
    public ModelAndView add(String title, String content,@RequestParam("file") MultipartFile file){
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(content) || file==null){
            return new ModelAndView("redirect:/notice/?pageSize=20&pageNum=1");
        }
        return noticeService.add(title,content,file);
    }
    @GetMapping("/")
    public ModelAndView notice(Integer pageSize,Integer pageNum,String name){
        ModelAndView mv = new ModelAndView("notice");
        if (StringUtils.isEmpty(name)){
            mv.addObject("notice",noticeService.page(new Page<>(pageNum,pageSize)));
        }else{
            mv.addObject("notice",noticeService.page(new Page<>(pageNum,pageSize),new LambdaQueryWrapper<Notice>().like(Notice::getTitle,name)));
        }
        return mv;
    }
    @GetMapping("/delete")
    public ModelAndView del(Integer id){
        ModelAndView mv = new ModelAndView("redirect:/notice/?pageSize=20&pageNum=1");
        if (!noticeService.removeById(id)){
            mv.addObject("msg","删除失败");
        }
        mv.addObject("msg","删除成功");
        return mv;
    }
}
