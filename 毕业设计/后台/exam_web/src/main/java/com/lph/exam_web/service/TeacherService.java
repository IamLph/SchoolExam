package com.lph.exam_web.service;

import com.lph.exam_web.common.Result;

public interface TeacherService {
//    获取所有教师
    Result<Object> getAllTeacher();
//    根据ID获取教师信息
    Result<Object> getTeacherById(String teacherId);
//    教师登陆
    Result<Object> loginTeacher(String teacherId,String password);
//    分页查询教师
    Result<Object> getTeacherByPage(int totalPage);
//    修改教师信息
    Result<Object> updateTeacherInfo(String teacherName,String teacherPassword,String teacherId);
//    删除教师
    Result<Object> deleteTeacherById(String teacherId);
//    添加教师
    Result<Object> addTeacher(String teacherName,String teacherPassword);

}
