package com.example.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.entity.LaboratoryUser;
import com.example.web.service.LaboratoryUserService;
import com.example.web.mapper.LaboratoryUserMapper;
import com.example.web.util.Result;
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

    @Override
    public boolean audti(Integer id, Integer state) {
        LaboratoryUser user = getById(id);
        user.setState(state);
        return updateById(user);
    }

    @Override
    public Result sign(Integer userId, Integer id) {
        LaboratoryUser laboratoryUser = getById(id);

        laboratoryUser.setSign(1);
        laboratoryUser.setSignTime(new Date());
        if(updateById(laboratoryUser)){
            return Result.build(200,"签到成功",null);
        }else {
            return Result.build(500,"签到失败",null);
        }
    }
}




