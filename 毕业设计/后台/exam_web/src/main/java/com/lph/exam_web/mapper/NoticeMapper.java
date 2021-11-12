package com.lph.exam_web.mapper;

import com.lph.exam_web.pojo.ExamPaper;
import com.lph.exam_web.pojo.Notice;
import com.lph.exam_web.vo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoticeMapper {
//获取所有通知
    @Select("select * from notice")
    List<Notice> getAllNotice();
//    获取所有题 通知
    @Select("select count(*) from notice")
    int getAllTotal();

    @Select("select * from notice where noticeId = #{noticeId}")
    Notice getNoticeById(String noticeId);

    @Insert("INSERT into notice (noticeId,courseId,type,startTime,endTime,teacherId,isPublish) VALUES (#{noticeId},#{courseId},#{type},#{startTime},#{endTime},#{teacherId},'0')")
    int addNotice(String noticeId, String type, String startTime, String endTime,String teacherId,String courseId);
//    添加试卷试题
    @Insert({
            "<script>",
            "insert into exampaper(noticeId,questionId) values ",
            "<foreach collection='item' item='item' index='index' separator=','>",
            "(#{item.noticeId}, #{item.questionId})",
            "</foreach>",
            "</script>"
    })
    int addExamPaper(@Param(value = "item")List<ExamPaper> list);

    @Select("SELECT notice.noticeId, course.className,notice.type,notice.startTime,notice.endTime,teacher.teacherName,notice.isPublish from notice,course,teacher where notice.courseId = course.classId and notice.teacherId = teacher.teacherId ORDER BY startTime limit #{totalPage},#{size}")
    List<NoticeVo> getNoticeByPage(int totalPage, int size);

//    根据通知id获取通知信息(条数默认为1)
    @Select("SELECT notice.noticeId, course.className,notice.type,notice.startTime,notice.endTime,teacher.teacherName,notice.isPublish from notice,course,teacher where notice.courseId = course.classId and notice.teacherId = teacher.teacherId and notice.noticeId = #{noticeId} limit #{totalPage},#{size}")
    List<NoticeVo> getNoticeVoByNoticeId(String noticeId,int totalPage, int size);
//    根据课程号获取通知信息
    @Select("SELECT notice.noticeId, course.className,notice.type,notice.startTime,notice.endTime,teacher.teacherName,notice.isPublish from notice,course,teacher where notice.courseId = course.classId and notice.teacherId = teacher.teacherId and notice.courseId = #{courseId} ORDER BY startTime limit #{totalPage},#{size}")
    List<NoticeVo> getNoticeByCourseId(String courseId,int totalPage, int size);

    @Update("update notice set isPublish = #{type} where noticeId = #{noticeId}")
    int updateStatus(String type,String noticeId);
//    删除操作
    @Delete("DELETE from notice where noticeId = #{noticeId}")
    int deleteNoticeById(String noticeId);
//获取已经结束的考试的批改记录
    @Select("SELECT notice.noticeId, course.className,notice.type,teacher.teacherName,notice.isPublish from notice,course,teacher where notice.courseId = course.classId and notice.teacherId = teacher.teacherId and notice.isPublish = '2' ORDER BY notice.startTime")
    List<ExamResultVo> getExamResult();
//
    @Select("SELECT score.userId,`user`.`name`,score.score,score.live,score.finalscore from score,`user` where score.userId = `user`.userId and score.noticeId = #{noticeId}")
    List<ScoreVo> getExamByNoticeId(String noticeId);
//    修改分数
    @Update({"<script>",
            "<foreach collection='list' item='item' index='index' separator=';'> ",
            "update score set finalscore = #{item.finalscore} ",
            "where noticeId = #{item.noticeId} and userId = #{item.userId} ",
            "</foreach>",
            "</script>"})
    int setScoreByUserIdAndNoticeId(@Param(value = "list")List<SetScoreVo> list);

    @Update("update score set publish = '1' where noticeId = #{noticeId} ")
    int setScorePublic(String noticeId);

    @Select("SELECT notice.noticeId, course.className,notice.type,teacher.teacherName,notice.isPublish from notice,course,teacher where notice.courseId = course.classId and notice.teacherId = teacher.teacherId and notice.isPublish = '2' and notice.noticeId = #{noticeId}")
    List<ExamResultVo> getExamResultByNoticeId(String noticeId);
}
