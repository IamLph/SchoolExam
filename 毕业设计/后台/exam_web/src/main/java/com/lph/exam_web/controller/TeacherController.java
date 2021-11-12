package com.lph.exam_web.controller;

import com.lph.exam_web.common.Result;
import com.lph.exam_web.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@ResponseBody
@Controller
@Api(tags = "教师接口")
@RequestMapping("api/Teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("获取所有教师")
    @GetMapping("/getAllTeacher")
    public Result<Object> getAllTeacher(){
        return teacherService.getAllTeacher();
    }


    @ApiOperation("根据id获取教师")
    @GetMapping("/getTeacherById")
    public Result<Object> getTeacherById(@RequestParam(value = "teacherId")String teacherId){
        return teacherService.getTeacherById(teacherId);
    }

    @ApiOperation("教师登陆")
    @GetMapping("/loginTeacher")
    public Result<Object> loginTeacher(@RequestParam(value = "id")String teacherId,
                                       @RequestParam(value = "password")String password){
        return teacherService.loginTeacher(teacherId, password);
    }

    @ApiOperation("分页查询教师")
    @GetMapping("/getTeacherByPage")
    public Result<Object> getTeacherByPage(@RequestParam(value = "totalPage")int totalPage){
        return teacherService.getTeacherByPage(totalPage);
    }

    @ApiOperation("修改教师信息")
    @PostMapping("/updateTeacherInfo")
    public Result<Object> updateTeacherInfo(@RequestParam(value = "name") String teacherName,
                                            @RequestParam(value = "password",required = false) String teacherPassword,
                                            @RequestParam(value = "id")String teacherId){
        return teacherService.updateTeacherInfo(teacherName, teacherPassword, teacherId);
    }

    @ApiOperation("删除教师")
    @PostMapping("/deleteTeacherById")
    public Result<Object> deleteTeacherById(@RequestParam(value = "id")String teacherId){
        return teacherService.deleteTeacherById(teacherId);
    }

    @ApiOperation("加盐测试")
    @PostMapping("/addTeacher")
    public Result<Object> addTeacher(@RequestParam(value = "teacherName")String teacherName,
                                     @RequestParam(value = "teacherPassword")String teacherPassword){
        return teacherService.addTeacher(teacherName, teacherPassword);
    }
}
