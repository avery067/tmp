package com.example.web.service;

import com.example.web.entity.Device;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.web.util.Result;
import org.springframework.web.multipart.MultipartFile;

/**
* @author avery
* @description 针对表【device】的数据库操作Service
* @createDate 2024-04-30 14:48:45
*/
public interface DeviceService extends IService<Device> {

    Result add(Integer userId, String name, String no, Integer type, String remark, MultipartFile file);

    void edit(Integer id, String note);

    Object listByUser(Integer userId);
}
