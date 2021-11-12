package com.lph.exam_web.controller;

import com.lph.exam_web.common.Code;
import com.lph.exam_web.common.Result;
import com.lph.exam_web.service.CourseService;
import com.lph.exam_web.service.VideoService;
import com.lph.exam_web.service.impl.VideoServiceImpl;
import com.lph.exam_web.util.QiniuUpload;
import com.lph.exam_web.util.RandomCount;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@ResponseBody
@Controller
@Api(tags = "课程接口")
@RequestMapping("api/Course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation("获取所有课程")
    @GetMapping("/getAllCourse")
    public Result<Object> getAllCourse(){
        return courseService.getAllCourse();
    }

    @ApiOperation("根据课程Id获取课程")
    @GetMapping("/getCourseById")
    public Result<Object> getCourseById(@RequestParam(value = "courseId")String courseId){
        return courseService.getCourseById(courseId);
    }
//上传文件
    @ApiOperation("(添加课程)上传课程文件")
    @PostMapping("/uploadFile")
    public Result<Object> uploadFile(@RequestParam(value = "file") MultipartFile file,
                                     @RequestParam(value = "className")String className,
                                     @RequestParam(value = "classTeacherName")String classTeacherName) {
        String name = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+"img"+name.substring(name.lastIndexOf("."));
        try {
            String fileUrl = "http://"+QiniuUpload.updateFile(file, fileName);
            if(fileUrl != null || fileUrl != ""){
                return courseService.addCourse(fileUrl,className,classTeacherName);
            }else{
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"图片上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"图片上传失败");
    }

    @ApiOperation("修改课程信息")
    @PostMapping("/updateCourseById")
    public Result<Object> updateCourseById(@RequestParam(value = "classId")String classId,
                                           @RequestParam(value = "className")String className,
                                           @RequestParam(value = "classTeacherName")String classTeacherName){
        return courseService.updateCourse(classId, className, classTeacherName);
    }
    @ApiOperation("删除课程")
    @PostMapping("/deleteCourseById")
    public Result<Object> deleteCourseById(@RequestParam(value = "classId")String classId){
        return courseService.deleteCourse(classId);
    }

    @ApiOperation("课时视频信息修改")
    @PostMapping("/updateVideoInfo")
    public Result<Object> updateVideoInfo(@RequestParam(value = "img") MultipartFile imgfile,
                                          @RequestParam(value = "mp5") MultipartFile mp5file,
                                     @RequestParam(value = "videoName")String videoName,
                                     @RequestParam(value = "videoInfo")String videoInfo,
                                      @RequestParam(value = "videoUnit")String videoUnit,
                                      @RequestParam(value = "id")String id) {
        String imgName = imgfile.getOriginalFilename();
        String mp5Name = mp5file.getOriginalFilename();
//        返回的地址
        String img = System.currentTimeMillis()+"img"+imgName.substring(imgName.lastIndexOf("."));
        String mp5 = System.currentTimeMillis()+"mp5"+mp5Name.substring(mp5Name.lastIndexOf("."));
        try {
            String imgUrl = "http://"+QiniuUpload.updateFile(imgfile, img);
            String mp5Url = "http://"+QiniuUpload.updateFile(mp5file, mp5);
            if(imgUrl != null || mp5Url!=""){
                return courseService.updateVideoInfo(mp5Url,videoName,videoInfo,videoUnit,imgUrl,id);
            }else{
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
    }

    @ApiOperation("添加课程视频的信息")
    @PostMapping("/addVideoInfo")
    public Result<Object> addVideoInfo(@RequestParam(value = "img") MultipartFile imgfile,
                                          @RequestParam(value = "mp5") MultipartFile mp5file,
                                          @RequestParam(value = "videoName")String videoName,
                                          @RequestParam(value = "videoInfo")String videoInfo,
                                          @RequestParam(value = "videoUnit")String videoUnit,
                                          @RequestParam(value = "classId")String classId) {
        String imgName = imgfile.getOriginalFilename();
        String mp5Name = mp5file.getOriginalFilename();
//        返回的地址
        String img = System.currentTimeMillis()+"img"+imgName.substring(imgName.lastIndexOf("."));
        String mp5 = System.currentTimeMillis()+"mp5"+mp5Name.substring(mp5Name.lastIndexOf("."));
//        随机视频Id
        String id = new RandomCount().getRandomId();
        try {
            String imgUrl = "http://"+QiniuUpload.updateFile(imgfile, img);
            String mp5Url = "http://"+QiniuUpload.updateFile(mp5file, mp5);
            if(imgUrl != null || mp5Url!=""){
                return courseService.addVideoInfo(id,classId,mp5Url,videoName,videoInfo,videoUnit,imgUrl);
            }else{
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
    }
}