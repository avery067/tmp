package com.example.web.ctrl.api;

import com.example.web.service.LaboratoryService;
import com.example.web.service.LaboratoryUserService;
import com.example.web.util.Result;
import com.example.web.vo.CommonPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Avery
 * @create 2024-04-25 14:34
 * @description 用户实验室
 */
@RestController
@RequestMapping("/api/laboratory")
public class ApiLaboratoryCtrl {
    @Autowired
    private LaboratoryService laboratoryService;
    @Autowired
    private LaboratoryUserService laboratoryUserService;

    /**
     * 获取实验室信息
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Integer id){
        return Result.build(200,"success",laboratoryService.get(id));
    }
    /**
     * 获取实验室列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/list")
    public Result list(Integer pageSize,Integer pageNum){
        CommonPageVo vo = laboratoryService.laboratory(pageSize,pageNum,null);
        return Result.build(200,"success",vo);
    }

    /**
     * 获取用户预约列表
     * @param pageSize
     * @param pageNum
     * @param userId
     * @return
     */
    @GetMapping("/list/user")
    public Result listUser(Integer pageSize,Integer pageNum,Integer userId){
        CommonPageVo vo = laboratoryService.listByUser(pageSize,pageNum,userId);
        return Result.build(200,"success",vo);
    }
    @PostMapping("/apply")
    public Result apply(Integer id,Integer userId,String applyTime,String no){
        return laboratoryService.apply(id,userId,applyTime,no);
    }

    /**
     * 取消预约
     * @param id
     * @param userId
     * @return
     */
    @PostMapping("/cancel")
    public Result cancel(Integer id,Integer userId){
        return Result.build(200,"success",laboratoryService.cancel(id,userId));
    }

    @PostMapping("/sign")
    public Result sign(Integer userId,Integer id){
        return laboratoryUserService.sign(userId,id);
    }
}
