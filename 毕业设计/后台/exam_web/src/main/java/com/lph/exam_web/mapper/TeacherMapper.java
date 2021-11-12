package com.lph.exam_web.mapper;

import com.lph.exam_web.pojo.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {

    @Select("select * from teacher")
    List<Teacher> getAllTeacher();
//根据id查询教师
    @Select("select * from teacher where teacherId = #{teacherId}")
    Teacher getTeacherById(String teacherId);

//    分页查询教师用户
    @Select("SELECT * from teacher limit #{totalPage},#{size}")
    List<Teacher> getTeacherByPage(int totalPage,int size);
//获取教师数目
    @Select("select count(*) from teacher")
    int getAllTotal();
//    修改姓名
    @Update("update teacher set teacherName = #{teacherName} where teacherId = #{teacherId}")
    int updateTeacherName(String teacherName,String teacherId);

//    修改姓名和密码
    @Update("update teacher set teacherName = #{teacherName},teacherPassword = #{teacherPassword} where teacherId = #{teacherId}")
    int updateTeacher(String teacherName,String teacherPassword,String teacherId);

//    根据ID删除教师
    @Delete("delete from teacher where teacherId = #{teacherId}")
    int deleteTeacherById(String teacherId);

//    添加教师
    @Insert("insert into teacher (teacherId,teacherName,teacherPassword) values (#{teacherId},#{teacherName},#{teacherPassword})")
    int addTeacher(String teacherId,String teacherName,String teacherPassword);
}
