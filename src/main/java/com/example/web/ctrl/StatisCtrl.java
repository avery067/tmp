package com.example.web.ctrl;

import com.example.web.mapper.LaboratoryMapper;
import com.example.web.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Avery
 * @create 2024-04-30 16:27
 * @description
 */
@RequestMapping("/statis")
@RestController
public class StatisCtrl {
    @Autowired
    private LaboratoryMapper laboratoryMapper;
    @GetMapping("/current")
    public Result current() {
        return Result.build(200, "success", laboratoryMapper.statisCurrent());
    }
    @GetMapping("/persons")
    public Result persons(){
        return Result.build(200,"success",laboratoryMapper.statisPersons());
    }
}
