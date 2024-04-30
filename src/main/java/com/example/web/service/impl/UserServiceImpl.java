package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.entity.User;
import com.example.web.service.UserService;
import com.example.web.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
* @author avery
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-04-25 10:04:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public Page<User> user(Integer pageSize, Integer pageNum,String name) {
        if(StringUtils.isEmpty(name)){
            return page(new Page<>(pageNum,pageSize));
        }else{
            return page(new Page<>(pageNum,pageSize),new LambdaQueryWrapper<User>().like(User::getUserName,name));
        }

    }

    @Override
    public ModelAndView add(String userName, String passWord) {
        if (userName != null && passWord != null) {
            User user = new User();
            user.setUserName(userName);
            user.setPassWord(passWord);
            this.save(user);
        }
        return new ModelAndView("redirect:/user?pageSize=20&pageNum=1");
    }

    @Override
    public ModelAndView edit(Integer id,String userName, String passWord, String mobile, String no) {
        if (id != null && userName != null && passWord != null) {
            User user = new User();
            user.setId(id);
            user.setUserName(userName);
            user.setPassWord(passWord);
            user.setMobile(mobile);
            user.setNo(no);
            this.updateById(user);
        }
        return new ModelAndView("redirect:/user?pageSize=20&pageNum=1");
    }

    @Override
    public ModelAndView delete(Integer id) {
        if (id != null) {
            this.removeById(id);
        }
        return new ModelAndView("redirect:/user?pageSize=20&pageNum=1");
    }

    @Override
    public User findByUserNameAndPassWord(String userName, String passWord) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getUserName,userName).eq(User::getPassWord,passWord));
    }

    @Override
    public User findByUserName(String userName) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getUserName,userName));
    }
}




