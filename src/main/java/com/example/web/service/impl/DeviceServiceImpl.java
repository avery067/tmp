package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.entity.Device;
import com.example.web.service.DeviceService;
import com.example.web.mapper.DeviceMapper;
import com.example.web.util.FileUtil;
import com.example.web.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
* @author avery
* @description 针对表【device】的数据库操作Service实现
* @createDate 2024-04-30 14:48:45
*/
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device>
    implements DeviceService{

    @Override
    public Result add(Integer userId, String name, String no, Integer type, String remark, MultipartFile file) {
        String img = FileUtil.save(file);

        Device device = new Device();
        device.setName(name);
        device.setNo(no);
        device.setType(type);
        device.setRemark(remark);
        device.setImage(img);
        device.setUserId(userId);
        if(save(device)){
            return Result.build(200,"添加成功",null);
        }else{
            return Result.build(400,"添加失败",null);
        }
    }

    @Override
    public void edit(Integer id, String note) {
        Device de = getById(id);
        de.setNote(note);
        updateById(de);
    }

    @Override
    public Object listByUser(Integer userId) {

        return list(new LambdaQueryWrapper<Device>().eq(Device::getUserId,userId));
    }
}




