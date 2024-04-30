package com.example.web.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.web.entity.Lock;
import com.example.web.service.LockService;
import com.example.web.service.UserService;
import com.example.web.vo.CommonPageVo;
import com.example.web.vo.LockVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Avery
 * @create 2024-04-30 16:55
 * @description
 */
@Controller
@RequestMapping("/lock")
public class BlockCtrl {
    @Autowired
    private LockService lockService;
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public ModelAndView lock(Integer pageSize,Integer pageNum){
        //判断lock.num 大于等于3
        ModelAndView view = new ModelAndView("lock");
        Page<Lock> page = lockService.page(new Page<>(pageNum,pageSize),new LambdaQueryWrapper<Lock>()
                .ge(Lock::getNum,3));

        List<LockVo> vos = new ArrayList<>();
        CommonPageVo vo = new CommonPageVo();
        vo.setPageNum(pageNum);
        vo.setPageSize(pageSize);
        vo.setTotal(page.getTotal());
        page.getRecords().stream().forEach(lock -> {
            LockVo tmp = new LockVo();
            BeanUtils.copyProperties(lock,tmp);
            tmp.setUserName(userService.getById(lock.getUserId()).getUserName());
        });
        vo.setData(vos);
        view.addObject("locks",vo);
        return view;
    }
    //解锁
    @GetMapping("/unlock")
    public ModelAndView unlock(Integer id){
        lockService.removeById(id);
        return new ModelAndView("redirect:/lock/?pageSize=20&pageNum=1");
    }
}
