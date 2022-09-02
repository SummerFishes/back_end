package com.example.controller;

import com.example.dao.UserClientMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class Test1Controller {
    @Resource
    UserClientMapper userClientMapper;

    @PostMapping("searchUser")
    @CrossOrigin
    public int searchUser(@RequestBody String userName){
        System.out.println(userName);
//        try {
//            // 模拟操作数据库等 耗时2s
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return "Test1成功";
        return userClientMapper.searchId(userName);
    }
}
