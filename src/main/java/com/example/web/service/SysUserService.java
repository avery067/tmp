package com.example.web.service;

import com.example.web.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.servlet.ModelAndView;

/**
* @author avery
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2024-04-25 10:04:17
*/
public interface SysUserService extends IService<SysUser> {

    ModelAndView login(String sysUserName, String sysUserPassword);

    ModelAndView edit(Integer id, String sysUserName, String sysUserPassWord);

    ModelAndView add(String sysUserName, String sysUserPassWord);
}
