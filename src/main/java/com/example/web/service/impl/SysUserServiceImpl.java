package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.entity.SysUser;
import com.example.web.service.SysUserService;
import com.example.web.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
* @author avery
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2024-04-25 10:04:17
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Override
    public ModelAndView login(String sysUserName, String sysUserPassword) {
        // 查询用户
        LambdaQueryWrapper<SysUser> wrapper =
                new LambdaQueryWrapper<SysUser>().eq(SysUser::getSysUserName, sysUserName).eq(SysUser::getSysPassWord, sysUserPassword);
        if (this.getOne(wrapper) != null) {
            return new ModelAndView("redirect:/main");
        }
        return new ModelAndView("index");
    }

    @Override
    public ModelAndView edit(Integer id, String sysUserName, String sysUserPassWord) {
        SysUser user = getById(id);
        if (user != null) {
            user.setSysUserName(sysUserName);
            user.setSysPassWord(sysUserPassWord);
            updateById(user);
        }
        return new ModelAndView("redirect:/sys/user/?pageSize=20&pageNum=1");
    }

    @Override
    public ModelAndView add(String sysUserName, String sysUserPassWord) {

        if (sysUserName != null && sysUserPassWord != null) {
            SysUser user = new SysUser();
            user.setSysUserName(sysUserName);
            user.setSysPassWord(sysUserPassWord);
            save(user);
        }
        return new ModelAndView("redirect:/sys/user/?pageSize=20&pageNum=1");
    }
}




