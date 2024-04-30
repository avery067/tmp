package com.example.web.ctrl.api;

import com.example.web.entity.User;
import com.example.web.service.UserService;
import com.example.web.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Avery
 * @create 2024-04-25 14:30
 * @description
 */
@RestController
@RequestMapping("/api/user")
public class ApiUserCtrl {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(String userName, String passWord){

        if (!StringUtils.isNotBlank(userName) && !StringUtils.isNotBlank(passWord)){
            return Result.build(400,"用户名或密码不能为空",null);
        }
        if(null == userService.findByUserName(userName)){
            return Result.build(404,"用户名或密码错误",null);
        }
        User user = userService.findByUserNameAndPassWord(userName,passWord);
        if (user == null){
            return Result.build(401,"用户名或密码错误",null);
        }
        return Result.build(200,"登录成功",user);
    }
    @GetMapping("/get/{id}")
    public Result getUserInfo(@PathVariable("id")Integer id){
        return Result.build(000,"获取成功",userService.getById(id));
    }
    @PostMapping("/edit")
    public Result edit(@RequestBody User user){
        if(user.getId()== null){
            return Result.build(400,"错误的用户信息",null);
        }
        if(userService.getById(user.getId()) == null ){
            return Result.build(400,"用户不存在",null);
        }
        if(userService.updateById(user)){
            return Result.build(200,"修改成功",null);
        }
        return Result.build(400,"修改失败",null);
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        if (!StringUtils.isNotBlank(user.getUserName())&& !StringUtils.isNotBlank(user.getPassWord())){
            return Result.build(400,"用户名或密码不能为空",null);
        }
        if(null !=userService.findByUserName(user.getUserName())){
            return Result.build(400,"用户名已存在",null);
        }
        if(userService.save(user)){
            return Result.build(200,"注册成功",null);
        }
        return Result.build(400,"注册失败",null);
    }
}
