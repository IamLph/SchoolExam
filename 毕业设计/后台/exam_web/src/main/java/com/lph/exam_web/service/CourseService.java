package com.lph.exam_web.service;

import com.lph.exam_web.common.Result;

public interface CourseService {
//    获取所有的课程
    Result<Object> getAllCourse();
//    根据Id获取课程
    Result<Object> getCourseById(String courseId);
//    添加课程
    Result<Object> addCourse(String classImgUrl,String className,String classTeacherName);
//    编辑课程信息
    Result<Object> updateCourse(String classId,String className,String classTeacherName);
//    删除课程
    Result<Object> deleteCourse(String classId);
    //    修改视频的信息
    Result<Object> updateVideoInfo(String videoUrl,String videoName,String videoInfo,String videoUnit,String videoImg,String videoId);
//    添加视频
    Result<Object> addVideoInfo(String videoId,String classId,String videoUrl, String videoName, String videoInfo, String videoUnit, String videoImg);
}
