package com.lph.exam_web.mapper;

import com.lph.exam_web.pojo.User;
import com.lph.exam_web.vo.ExamHistory;
import com.lph.exam_web.vo.ExamInfoVo;
import com.lph.exam_web.vo.UserVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper{

    @Select("select * from user")
    List<User> selectUser();

    @Select("select * from user where userId = #{userId}")
    User getUserById(@Param("userId")String userId);

    @Insert("insert into user (name,userId,password,nation) VALUES (#{name},#{userId},#{password},'汉族')")
    int registerUser(@Param("name")String name,@Param("userId")String userId,@Param("password")String password);

    @Update("update user set name = #{name},sex = #{sex},nation=#{nation},nationPlace=#{nationPlace},phone=#{phone},department=#{department},grade=#{grade},branch=#{branch} where userId = #{userId}")
    int updateUserInfo(UserVo userVo);

    @Delete("delete from user where userId = #{userId}")
    int deleteUserById(@Param("userId")String userId);

    @Update("update user set password = #{password} where userId = #{userId}")
    int updateUserPassWord(@Param("userId")String userId,@Param("password")String password);
//根据用户信息发布考试通知
    @Select("SELECT notice.noticeId,notice.type,notice.startTime,notice.endTime,notice.teacherId,teacher.teacherName from notice,teacher where notice.isPublish = '1' and notice.teacherId = teacher.teacherId ORDER BY notice.startTime asc")
    List<ExamInfoVo> getExamNotice();

    @Insert("INSERT into score (userId,teacherId,noticeId,score,live) values(#{userId},#{teacherId},#{noticeId},#{score},#{live})")
    int submitExamPaper(String userId, int score, int live, String teacherId, String noticeId);

//    查询是否提交过了
    @Select("select count(*) from score where userId = #{userId} and noticeId = #{noticeId}")
    int getIsSubmit(String userId,String noticeId);

    @Update("update examinfo set isFinsh = 'true' where userId = #{userId} and noticeId = #{noticeId}")
    int updateExamPaperByUserId(String userId,String noticeId);

//    @Select("select notice.type,notice.startTime,score.finalscore,score.publish from examInfo,notice,score where examinfo.noticeId = notice.noticeId and notice.noticeId = score.noticeId and examinfo.userId = #{userId} ORDER BY notice.startTime asc")
//    List<ExamHistory> getExamHistory(String userId);
    @Select("SELECT notice.type,notice.startTime,score.finalscore,score.publish,course.classImgUrl from notice,score,course where score.userId = #{userId} and score.noticeId = notice.noticeId and notice.courseId = course.classId ORDER BY notice.startTime asc")
    List<ExamHistory> getExamHistory(String userId);
//    获取学生数目
    @Select("select count(*) from user")
    int getAllTotal();
//    分页查询用户
    @Select("SELECT * from user limit #{totalPage},#{size}")
    List<User> getUserByPage(int totalPage, int size);

    @Update("update user set headImg = #{headImg} where userId = #{userId}")
    int updateUserImg(String headImg, String userId);
}
