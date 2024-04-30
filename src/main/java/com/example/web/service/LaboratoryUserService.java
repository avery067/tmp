package com.example.web.service;

import com.example.web.entity.LaboratoryUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.web.util.Result;

/**
* @author avery
* @description 针对表【laboratory_user】的数据库操作Service
* @createDate 2024-04-25 10:04:17
*/
public interface LaboratoryUserService extends IService<LaboratoryUser> {

    boolean audti(Integer id, Integer state);

    Result sign(Integer userId, Integer id);
}
