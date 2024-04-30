package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.entity.Laboratory;
import com.example.web.entity.LaboratoryUser;
import com.example.web.entity.Lock;
import com.example.web.service.LaboratoryService;
import com.example.web.mapper.LaboratoryMapper;
import com.example.web.service.LaboratoryUserService;
import com.example.web.service.LockService;
import com.example.web.service.UserService;
import com.example.web.util.DateUtil;
import com.example.web.util.FileUtil;
import com.example.web.util.Result;
import com.example.web.vo.CommonPageVo;
import com.example.web.vo.LaboratoryUsersVo;
import com.example.web.vo.LaboratoryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author avery
* @description 针对表【laboratory】的数据库操作Service实现
* @createDate 2024-04-25 10:04:17
*/
@Service
public class LaboratoryServiceImpl extends ServiceImpl<LaboratoryMapper, Laboratory>
    implements LaboratoryService{
    @Autowired
    private LaboratoryUserService laboratoryUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private LockService lockService;
    @Override
    public boolean add(String name, Integer max, MultipartFile file) {
        Laboratory laboratory = new Laboratory();
        laboratory.setName(name);
        laboratory.setMax(max);
        String img  = FileUtil.save(file);
        laboratory.setImg(img);
        if (this.save(laboratory)) {
           return false;
        }
        return true;
    }

    @Override
    public CommonPageVo laboratory(Integer pageSize, Integer pageNum,String name) {
        List<LaboratoryVo> list = new ArrayList<>();
        IPage<Laboratory> page = null;
        if (StringUtils.isEmpty(name)){
            page = this.page(new Page<Laboratory>(pageNum,pageSize));
        }else{
            page = this.page(new Page<Laboratory>(pageNum,pageSize),new LambdaQueryWrapper<Laboratory>().like(Laboratory::getName,name));
        }
        page.getRecords().stream().forEach(laboratory -> {
            LaboratoryVo vo = new LaboratoryVo();
            vo.setId(laboratory.getId());
            vo.setName(laboratory.getName());
            vo.setMax(laboratory.getMax());
            vo.setImg(laboratory.getImg());
            vo.setCurrent(laboratory.getMax()-laboratoryUserService.list(new LambdaQueryWrapper<LaboratoryUser>().eq(LaboratoryUser::getState,1).eq(LaboratoryUser::getLaboratoryId,laboratory.getId())).size());
            list.add(vo);
        });
        CommonPageVo vo = new CommonPageVo();
        vo.setTotal(page.getTotal());
        vo.setPageSize(pageSize);
        vo.setPageNum(pageNum);
        vo.setData(list);

       return vo;
    }

    @Override
    public boolean edit(Integer id, String name, Integer max,MultipartFile file) {

        Laboratory laboratory = this.getById(id);
        if (laboratory != null) {
            laboratory.setName(name);
            laboratory.setMax(max);
            String img  = FileUtil.save(file);
            laboratory.setImg(img);
            if (!this.updateById(laboratory)) {
               return false;
            }
        }
        return true;
    }
    //预约管理
    @Override
    public CommonPageVo laboratoryUser(Integer pageSize,Integer pageNum) {
        CommonPageVo resultVo = new CommonPageVo();
        List<LaboratoryUsersVo> list = new ArrayList<>();
        IPage<LaboratoryUser> page = laboratoryUserService.page(new Page<>(pageNum,pageSize),new LambdaQueryWrapper<LaboratoryUser>().orderByDesc(LaboratoryUser::getId));
        page.getRecords().stream().forEach(users -> {
            this.getById(users.getLaboratoryId()).getName();
            userService.getById(users.getUserId()).getUserName();
            LaboratoryUsersVo vo = new LaboratoryUsersVo();
            vo.setId(users.getId());
            vo.setName(this.getById(users.getLaboratoryId()).getName());
            vo.setUserName(userService.getById(users.getUserId()).getUserName());
            vo.setCreateTime(DateUtil.format(users.getCreateTime()));
            vo.setState(users.getState());
            vo.setSign(users.getSign());
            vo.setNo(users.getNo());
            if(users.getSignTime() != null){
                vo.setSignTime(DateUtil.format(users.getSignTime()));
            }
            list.add(vo);
        });
        resultVo.setData(list);
        resultVo.setTotal(page.getTotal());
        resultVo.setPageSize(pageSize);
        resultVo.setPageNum(pageNum);
        return resultVo;
    }

    @Override
    public CommonPageVo listByUser(Integer pageSize, Integer pageNum, Integer userId) {
        CommonPageVo resultVo = new CommonPageVo();
        List<LaboratoryUsersVo> list = new ArrayList<>();
        IPage<LaboratoryUser> page = laboratoryUserService.page(new Page<>(pageNum,pageSize),new LambdaQueryWrapper<LaboratoryUser>()
                .eq(LaboratoryUser::getUserId,userId)
                .orderByDesc(LaboratoryUser::getId));
        page.getRecords().stream().forEach(users -> {
            this.getById(users.getLaboratoryId()).getName();
            userService.getById(users.getUserId()).getUserName();
            LaboratoryUsersVo vo = new LaboratoryUsersVo();
            vo.setId(users.getId());
            vo.setName(this.getById(users.getLaboratoryId()).getName());
            vo.setUserName(userService.getById(users.getUserId()).getUserName());
            vo.setCreateTime(DateUtil.format(users.getCreateTime()));
            vo.setState(users.getState());
            vo.setSign(users.getSign());
            vo.setNo(users.getNo());
            if (users.getSignTime() != null){
                vo.setSignTime(DateUtil.format(users.getSignTime()));
            }
            list.add(vo);
        });
        resultVo.setData(list);
        resultVo.setTotal(page.getTotal());
        resultVo.setPageSize(pageSize);
        resultVo.setPageNum(pageNum);
        return resultVo;
    }

    @Override
    public boolean cancel(Integer id, Integer userId) {
        LaboratoryUser one = laboratoryUserService.getOne(new LambdaQueryWrapper<LaboratoryUser>().eq(LaboratoryUser::getId, id).eq(LaboratoryUser::getUserId, userId));
        one.setState(3);
        return  laboratoryUserService.updateById(one);
    }

    @Override
    public Object get(Integer id) {
        Laboratory laboratory = getById(id);
        int count = laboratoryUserService.list(new LambdaQueryWrapper<LaboratoryUser>().eq(LaboratoryUser::getLaboratoryId,id).eq(LaboratoryUser::getState,1)).size();
        LaboratoryVo vo = new LaboratoryVo();
        if (laboratory != null) {
            vo.setId(laboratory.getId());
            vo.setName(laboratory.getName());
            vo.setMax(laboratory.getMax());
            vo.setCurrent(laboratory.getMax()-count);
            vo.setImg(laboratory.getImg());
            return vo;
        }
        return null;
    }

    @Override
    public Result apply(Integer id, Integer userId,String applyTime,String no) {

        List<LaboratoryUser> list = laboratoryUserService.list(new LambdaQueryWrapper<LaboratoryUser>()
                .eq(LaboratoryUser::getLaboratoryId,id)
                .eq(LaboratoryUser::getUserId,userId)
                .eq(LaboratoryUser::getState,0)
                .or()
                .eq(LaboratoryUser::getState,1)
                );
        String now = DateUtil.format(DateUtil.parseApplyTime(applyTime),"yyyy-MM-dd");
        AtomicBoolean flag = new AtomicBoolean(false);
        list.stream().forEach(item->{
            String time = DateUtil.format(item.getCreateTime(),"yyyy-MM-dd");
           if(time.equals(now)){
               flag.set(true);
           }
        });

        if(flag.get()){
            return Result.build(407,"您已经预约过了,请不要重复预约",null);
        }

        Lock lock = lockService.getOne(new LambdaQueryWrapper<Lock>().eq(Lock::getUserId,userId));
        if(lock != null){
            if (lock.getNum()>=3){
                return Result.build(409,"您已被拉黑，请联系管理员",null);
            }
        }

        if (laboratoryUserService.list(new LambdaQueryWrapper<LaboratoryUser>().eq(LaboratoryUser::getLaboratoryId,id)).size() >= getById(id).getMax()){
            return Result.build(408,"实验室剩余容量不足",null);
        }
        LaboratoryUser laboratoryUser = new LaboratoryUser();
        laboratoryUser.setLaboratoryId(id);
        laboratoryUser.setUserId(userId);
        laboratoryUser.setCreateTime(DateUtil.parseApplyTimeDefault(applyTime));
        laboratoryUser.setState(0);
        laboratoryUser.setSign(0);
        laboratoryUser.setNo(no);
        laboratoryUser.setSignTime(null);
        if (laboratoryUserService.save(laboratoryUser)){
            return Result.build(200,"预约成功",null);
        }else{
            return Result.build(500,"服务器一场",null);
        }

    }

    @Override
    public void delete(Integer id) {
        this.removeById(id);
        laboratoryUserService.remove(new LambdaQueryWrapper<LaboratoryUser>().eq(LaboratoryUser::getLaboratoryId,id));
    }
}




