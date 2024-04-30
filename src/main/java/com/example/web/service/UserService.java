package com.example.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.web.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.servlet.ModelAndView;

/**
* @author avery
* @description 针对表【user】的数据库操作Service
* @createDate 2024-04-25 10:04:17
*/
public interface UserService extends IService<User> {

    Page<User> user(Integer pageSize, Integer pageNum,String name);

    ModelAndView add(String userName, String passWord);

    ModelAndView edit(Integer id,String userName, String passWord, String mobile, String no);

    ModelAndView delete(Integer id);


    User findByUserNameAndPassWord(String userName, String passWord);

    User findByUserName(String userName);
}
