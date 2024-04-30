package com.example.web.service;

import com.example.web.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
* @author avery
* @description 针对表【notice】的数据库操作Service
* @createDate 2024-04-25 10:04:17
*/
public interface NoticeService extends IService<Notice> {

    ModelAndView add(String title, String content, MultipartFile file);
}
