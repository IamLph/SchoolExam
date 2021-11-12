package com.lph.exam_web.service.impl;

import com.lph.exam_web.common.Code;
import com.lph.exam_web.common.Result;
import com.lph.exam_web.mapper.CourseMapper;
import com.lph.exam_web.pojo.Course;
import com.lph.exam_web.service.CourseService;
import com.lph.exam_web.util.RandomCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Repository
public class CourseServiceImpl implements CourseService {

    @Autowired(required = false)
    private CourseMapper courseMapper;

    @Override
    public Result<Object> getAllCourse() {
        List<Course> list = courseMapper.getAllCourse();
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> getCourseById(String courseId) {
        List<Course> list = courseMapper.getCourseById(courseId);
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> addCourse(String classImgUrl, String className, String classTeacherName) {
//        随机生成一个课程号
        String classId = new RandomCount().getCourseId();
        int result = courseMapper.addCourse(classId,classImgUrl, className, classTeacherName);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"添加失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"添加成功");
    }

    @Override
    public Result<Object> updateCourse(String classId, String className, String classTeacherName) {
        int result = courseMapper.updateCourse(classId, className, classTeacherName);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改成功");
    }

    @Override
    public Result<Object> deleteCourse(String classId) {
        int result = courseMapper.deleteCourse(classId);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"删除失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"删除成功");
    }
    @Override
    public Result<Object> updateVideoInfo(String videoUrl, String videoName, String videoInfo, String videoUnit, String videoImg, String videoId) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String publishTime = sdf.format(d);
        int result = courseMapper.updateVideoInfo(videoUrl,videoName,videoInfo,videoUnit,publishTime,videoImg,videoId);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改成功");
    }

    @Override
    public Result<Object> addVideoInfo(String videoId, String classId, String videoUrl, String videoName, String videoInfo, String videoUnit, String videoImg) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String publishTime = sdf.format(d);
        int result = courseMapper.addVideoInfo(videoId,classId,videoUrl,videoName,videoInfo,videoUnit,publishTime,videoImg);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"添加失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"添加成功");
    }

}
