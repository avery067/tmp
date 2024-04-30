package com.example.web.ctrl.api;

import com.example.web.service.DeviceService;
import com.example.web.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Avery
 * @create 2024-04-30 15:09
 * @description
 */
@RestController
@RequestMapping("/api/device")
public class ApiDeviceCtrl {
    @Autowired
    private DeviceService deviceService;

    /**
     * 添加报修
     * @param userId
     * @param name
     * @param no
     * @param type
     * @param remark
     * @param file
     * @return
     */
    @PostMapping("/add")
    public Result add(Integer userId,String name, String no, Integer type, String remark,@RequestParam("file") MultipartFile file){
        return deviceService.add(userId,name, no, type, remark, file);
    }

    /**
     * 用户报修记录
     * @param userId
     * @return
     */
    @GetMapping("/list/user")
    public Result listByUser(Integer userId){
        return Result.build(200,"查询成功",deviceService.listByUser(userId));
    }
}
