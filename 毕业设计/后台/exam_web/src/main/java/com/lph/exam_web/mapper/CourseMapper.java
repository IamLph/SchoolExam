package com.lph.exam_web.mapper;

import com.lph.exam_web.pojo.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseMapper {

    @Select("select * from course")
    List<Course> getAllCourse();

    @Select("select * from course where classId = #{courseId}")
    List<Course> getCourseById(String courseId);

//    添加课程
    @Insert("insert into course (classId,classImgUrl,className,classTeacherName) values (#{classId},#{classImgUrl},#{className},#{classTeacherName})")
    int addCourse(String classId,String classImgUrl,String className,String classTeacherName);
//    编辑课程
    @Update("update course set className = #{className},classTeacherName=#{classTeacherName} where classId = #{classId}")
    int updateCourse(String classId, String className, String classTeacherName);
//    删除课程
    @Delete("delete from course where classId = #{classId}")
    int deleteCourse(String classId);
    //    修改视频信息
    @Update("update videoinfo set videoUrl = #{videoUrl},videoName = #{videoName},videoInfo = #{videoInfo},videoUnit = #{videoUnit},publishTime = #{publishTime},videoImg =#{videoImg} where id = #{videoId}")
    int updateVideoInfo(String videoUrl, String videoName, String videoInfo, String videoUnit, String publishTime,String videoImg, String videoId);
//添加视频信息
    @Insert("insert into videoInfo (id,classId,videoUrl,videoName,videoInfo,videoUnit,publishTime,videoImg) values (#{videoId},#{classId},#{videoUrl},#{videoName},#{videoInfo},#{videoUnit},#{publishTime},#{videoImg})")
    int addVideoInfo(String videoId, String classId,String videoUrl, String videoName, String videoInfo, String videoUnit, String publishTime,String videoImg);

}
