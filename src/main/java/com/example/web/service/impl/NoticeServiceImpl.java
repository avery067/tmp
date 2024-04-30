package com.example.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.entity.Notice;
import com.example.web.service.NoticeService;
import com.example.web.mapper.NoticeMapper;
import com.example.web.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
* @author avery
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2024-04-25 10:04:17
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

    @Override
    public ModelAndView add(String title, String content, MultipartFile file) {
        ModelAndView view =  new ModelAndView("redirect:/notice/?pageSize=20&pageNum=1");
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setCreateTime(new Date());
        String img  = FileUtil.save(file);
        notice.setImg(img);
        if(!StringUtils.isNotEmpty(img)){
            view.addObject("msg","添加失败");
        }else  if (!this.save(notice)){
            view.addObject("msg","添加失败");
        }else{
            view.addObject("msg","添加成功");
        }

        return view;
    }
}




