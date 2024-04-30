package com.example.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.entity.Lock;
import com.example.web.service.LockService;
import com.example.web.mapper.LockMapper;
import org.springframework.stereotype.Service;

/**
* @author avery
* @description 针对表【lock】的数据库操作Service实现
* @createDate 2024-04-30 14:48:45
*/
@Service
public class LockServiceImpl extends ServiceImpl<LockMapper, Lock>
    implements LockService{

}




