package com.lph.exam_web.controller;

import com.lph.exam_web.common.Result;
import com.lph.exam_web.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@ResponseBody
@Controller
@Api(tags = "管理员接口")
@RequestMapping("api/Admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ApiOperation("获取所有管理员")
    @GetMapping("/getAllAdmin")
    public Result<Object> getAllAdmin(){
        return adminService.getAllAdmin();
    }

    @ApiOperation("管理员登陆")
    @PostMapping("/getAdminById")
    public Result<Object> getAdminById(@RequestParam(value = "id")String adminId,
                                       @RequestParam(value = "password")String adminPassword){
        return adminService.getAdminById(adminId,adminPassword);
    }
}
