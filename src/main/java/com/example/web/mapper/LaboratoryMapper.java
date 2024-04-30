package com.example.web.mapper;

import com.example.web.entity.Laboratory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author avery
* @description 针对表【laboratory】的数据库操作Mapper
* @createDate 2024-04-28 21:14:36
* @Entity com.example.web.entity.Laboratory
*/
public interface LaboratoryMapper extends BaseMapper<Laboratory> {
    List<Laboratory> statisPersons();
    List<Laboratory> statisCurrent();
}




