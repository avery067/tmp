package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.entity.LaboratoryUser;
import com.example.web.entity.Lock;
import com.example.web.service.LaboratoryUserService;
import com.example.web.mapper.LaboratoryUserMapper;
import com.example.web.service.LockService;
import com.example.web.service.UserService;
import com.example.web.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author avery
* @description 针对表【laboratory_user】的数据库操作Service实现
* @createDate 2024-04-25 10:04:17
*/
@Service
public class LaboratoryUserServiceImpl extends ServiceImpl<LaboratoryUserMapper, LaboratoryUser>
    implements LaboratoryUserService{

    @Autowired
    private LockService lockService;
    @Autowired
    private UserService userService;
    @Override
    public boolean audti(Integer id, Integer state) {
        LaboratoryUser user = getById(id);
        user.setState(state);
        return updateById(user);
    }

    @Override
    public Result sign(Integer userId, Integer id) {
        LaboratoryUser laboratoryUser = getById(id);
        boolean flag = false;
        //判断当前时间是否超出预约时间3分钟
        if(new Date().getTime() - laboratoryUser.getCreateTime().getTime() > 3*60*1000){
            Lock lock = lockService.getOne(new LambdaQueryWrapper<Lock>().eq(Lock::getUserId,userId));
            if(lock==null){
                lock.setUserId(userId);
                lock.setNum(0);
            }
            lock.setNum(lock.getNum()+1);
            laboratoryUser.setSign(2);
        }else{
            laboratoryUser.setSign(1);
        }

        laboratoryUser.setSignTime(new Date());
        if(updateById(laboratoryUser)){
            if(flag){
                return Result.build(200,"签到成功,您当前已超时,3次超时后将被拉黑！",null);
            }else{
                return Result.build(200,"签到成功",null);
            }

        }else {
            return Result.build(500,"签到失败",null);
        }
    }
}




