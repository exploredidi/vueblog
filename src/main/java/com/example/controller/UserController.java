package com.example.controller;


import com.example.commons.lang.Result;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chen
 * @since 2021-08-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public Object Hello(){
        User user = userService.getById(1L);
        return Result.succ(200,"成功",user);
    }

    @PostMapping("/save")
    public Object Save(@Validated  @RequestBody User user){

        return Result.succ(user);
    }


}
