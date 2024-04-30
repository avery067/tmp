package com.example.web.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.web.entity.LaboratoryUser;
import com.example.web.entity.Lock;
import com.example.web.service.LaboratoryUserService;
import com.example.web.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Avery
 * @create 2024-04-30 16:45
 * @description
 */
@Component
public class MyScheduledTask {

    @Autowired
    private LaboratoryUserService laboratoryUserService;
    @Autowired
    private LockService lockService;

    // 每分钟执行一次
    @Scheduled(fixedRate = 60000)
    public void executeTask() {
        laboratoryUserService.list(new LambdaQueryWrapper<LaboratoryUser>()
                .eq(LaboratoryUser::getSignState, 1)
                .or()
                .eq(LaboratoryUser::getSignTime,null)
        ).stream().forEach(item->{
                Lock lock = lockService.getOne(new LambdaQueryWrapper<Lock>()
                        .eq(Lock::getUserId, item.getId()));
                boolean flag = false;
                if(item.getSignTime()==null){
                    //判断当前时间是否超出预约时间3分钟
                    if(item.getCreateTime().getTime()-System.currentTimeMillis()>180000){
                        item.setSignState(2);
                        laboratoryUserService.updateById(item);
                        //加入黑名单
                        flag= true;
                    }
                }else{
                    //判断签到时间是否超出预约时间3分钟
                    if(item.getSignTime().getTime()-System.currentTimeMillis()>180000){
                        item.setSignState(2);
                        laboratoryUserService.updateById(item);
                        //加入黑名单
                        flag= true;
                    }

                }
                if(flag){
                    if(lock==null){
                        lock = new Lock();
                        lock.setUserId(item.getUserId());
                        lock.setNum(0);
                    }
                    lock.setNum(lock.getNum()+1);
                    lockService.saveOrUpdate(lock);
                }
        });

    }
}
