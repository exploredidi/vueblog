package com.example.controller;


import cn.hutool.crypto.SecureUtil;
import com.example.commons.lang.Result;
import com.example.entity.User;
import com.example.mail.RespBean;
import com.example.mail.service.EmployeeService;
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

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/hello")
    public Object Hello(){
        User user = userService.getById(1L);
        return Result.succ(200,"成功",user);
    }

    @PostMapping("/save")
    public Object Save(@Validated  @RequestBody User user){

        return Result.succ(user);
    }

    @PostMapping("/addUser")
    //添加用户（后期重构时候应该将其合并在User里，这里我是用了employee来表示）
    public RespBean addEmp(@RequestBody User employee) {

        String s = SecureUtil.md5(employee.getPassword());
        employee.setPassword(s);
        employee.setStatus(Integer.parseInt("00009"));
        Integer addEmp = employeeService.addEmp(employee);
        if ( addEmp == 1) {
            return RespBean.ok("添加成功!");
        }else if(addEmp == 0){
            return RespBean.error("邮箱重复！");
        }
        return RespBean.error("添加失败!");
    }


}
