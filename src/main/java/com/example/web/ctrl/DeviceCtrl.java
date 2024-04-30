package com.example.web.ctrl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.web.entity.Device;
import com.example.web.service.DeviceService;
import com.example.web.service.UserService;
import com.example.web.vo.CommonPageVo;
import com.example.web.vo.DeviceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Avery
 * @create 2024-04-30 15:16
 * @description
 */
@Controller
@RequestMapping("/device")
public class DeviceCtrl {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView device(Integer pageSize,Integer pageNum){
        ModelAndView mv = new ModelAndView();
        Page<Device> page = deviceService.page(new Page<>(pageNum, pageSize));
        CommonPageVo vo = new CommonPageVo();
        vo.setTotal(page.getTotal());
        vo.setPageNum(pageNum);
        vo.setPageSize(pageSize);
        List<DeviceVo> deviceVos =  new ArrayList<>();
        page.getRecords().stream().forEach(item->{
            DeviceVo deviceVo = new DeviceVo();
            BeanUtils.copyProperties(item,deviceVo);
            deviceVo.setUserName(userService.getById(item.getUserId()).getUserName());
            deviceVos.add(deviceVo);
        });
        vo.setData(deviceVos);
        mv.addObject("deviceList",vo);
        mv.setViewName("device");
        return mv;
    }

    @PostMapping("/edit")
    public ModelAndView edit(Integer id,String note){
        ModelAndView mv = new ModelAndView();
        deviceService.edit(id,note);
        mv.setViewName("redirect:/device/?pageSize=20&pageNum=1");
        return mv;
    }


}
