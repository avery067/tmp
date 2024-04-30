package com.example.web.ctrl.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.web.entity.Notice;
import com.example.web.service.NoticeService;
import com.example.web.util.Result;
import com.example.web.vo.CommonPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Avery
 * @create 2024-04-25 14:50
 * @description
 */
@RestController
@RequestMapping("/api/notice")
public class ApiNoticeCtrl {
    @Autowired
    private NoticeService service;
    @GetMapping("/list")
    public Result list(Integer pageSize,Integer pageNum) {
        IPage<Notice> page = service.page(new Page<>(pageNum,pageSize),new LambdaQueryWrapper<Notice>()
                .orderByDesc(Notice::getCreateTime));
        CommonPageVo vo = new CommonPageVo();
        vo.setData(page.getRecords());
        vo.setTotal(page.getTotal());
        vo.setPageSize(pageSize);
        vo.setPageNum(pageNum);
        return Result.build(200,"success",vo);
    }
}
