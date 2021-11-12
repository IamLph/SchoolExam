package com.lph.exam_web.mapper;

import com.lph.exam_web.pojo.Question;
import com.lph.exam_web.vo.QuestionCourseInfoVo;
import com.lph.exam_web.vo.UserQuestionVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
//    获取所有试题
    @Select("select * from question")
    List<Question> getAllQuestion();
//  获取收藏的试题
    @Select("select question.examId,question.courseId,question.title,question.type,question.optionList,question.answer,question.userAnswer,question.`explain`,userquestion.userFavor from question,userquestion where userquestion.userId = #{userId} and userquestion.examId = question.examId and question.type = #{type} and userquestion.userFavor = 'true';")
    List<UserQuestionVo> getQuestionByUserIdAndType(String userId,String type);
//  获取选择的练习题 随机十条
    @Select("select * from question where courseId =#{courseId} and type = #{type} ORDER BY RAND() LIMIT 10")
    List<Question> getQuestionByCourseIdAndType(String courseId,String type);
//  判断是否有过收藏,有没有试题关系,有关系的话就修改，没有的话就添加
    @Select("select count(*) from userquestion where userId = #{userId} and examId = #{examId}")
    int getUserAndExamRelation(String userId,String examId);
//  添加收藏
    @Insert("insert into userquestion (userId,examId,userFavor) values(#{userId},#{examId},#{userFavor})")
    int addFavor(String userId,String examId,String userFavor);
//  修改收藏
    @Update("update userquestion set userFavor = #{userFavor} where userId = #{userId} and examId = #{examId}")
    int updateFavor(String userId,String examId,String userFavor);
//    根据课程ID以及试题号获取综合练习题
    @Select("select * from question where courseId =#{courseId} ORDER BY RAND() LIMIT #{num}")
    List<Question> getQuestionByCourseIdType4(String courseId,int num);
//    有的话修改Wrong数据,或者斩掉错题
    @Update("update userquestion set isWrong = #{isWrong} where userId = #{userId} and examId = #{examId}")
    int updateUserWrong(String userId,String examId,String isWrong);
//    没有的话添加Wrong数据
    @Insert("insert into userquestion (userId,examId,isWrong) values(#{userId},#{examId},'true')")
    int insertUserWrong(String userId,String examId);
//    获取收藏的题目
    @Select("select question.examId,question.courseId,question.title,question.type,question.optionList,question.answer,question.userAnswer,question.`explain`,userquestion.userFavor from question,userquestion where userquestion.userId = #{userId} and userquestion.examId = question.examId and userquestion.userFavor = 'true'")
    List<UserQuestionVo> getFavorQuestion(String userId);
//    获取错题集
    @Select("select question.examId,question.courseId,question.title,question.type,question.optionList,question.answer,question.userAnswer,question.`explain`,userquestion.userFavor from question,userquestion where userquestion.userId =  #{userId} and userquestion.examId = question.examId and userquestion.isWrong = 'true'")
    List<UserQuestionVo> getWrongQuestion(String userId);
//    获取考试试题
//    @Select("SELECT question.examId,question.courseId,question.title,question.type,question.optionList,question.answer,question.userAnswer,question.`explain`,question.userFavor from exampaper,question,examinfo where examinfo.noticeId = exampaper.noticeId and exampaper.questionId = question.examId and examinfo.userId = #{userId}  and exampaper.noticeId = #{noticeId}")
//    List<Question> getExamPaper(String userId,String noticeId);
    @Select("SELECT question.examId,question.courseId,question.title,question.type,question.optionList,question.answer,question.userAnswer,question.`explain`,question.userFavor from question,exampaper where exampaper.questionId = question.examId and exampaper.noticeId = #{noticeId}")
    List<Question> getExamPaper(String noticeId);
//    分页获取试题,封装vo
    @Select("SELECT question.examId,course.className,question.courseId,question.title,question.type,question.optionList,question.answer,question.`explain` from question,course where question.courseId = course.classId limit #{totalPage},#{size}")
    List<QuestionCourseInfoVo> getQuestionByPage(int totalPage, int size);
//    获取所有试题
    @Select("select count(*) from question")
    int getAllTotal();
//    根据id获取试题
    @Select("SELECT question.examId,course.className,question.courseId,question.title,question.type,question.optionList,question.answer,question.`explain` from question,course where question.courseId = course.classId and question.examId = #{examId}")
    List<QuestionCourseInfoVo> getQuestionById(String examId);
//    只根据题目类型选择试题
    @Select("SELECT question.examId,course.className,question.courseId,question.title,question.type,question.optionList,question.answer,question.`explain` from question,course where question.courseId = course.classId and question.type = #{questionType} limit #{totalPage},#{size}")
    List<QuestionCourseInfoVo> getQuestionByQuestionType(String questionType,int totalPage, int size);
//    只根据题目类型获取试题的总条数
    @Select("select count(*) from question where type = #{questionType}")
    int getAllTotalByQuestion(String questionType);
//    只根据课程类型选择试题
    @Select("SELECT question.examId,course.className,question.courseId,question.title,question.type,question.optionList,question.answer,question.`explain` from question,course where question.courseId = course.classId and question.courseId = #{courseId} limit #{totalPage},#{size}")
    List<QuestionCourseInfoVo> getQuestionByCourseId(String courseId,int totalPage, int size);
//    只根据课程类型选择试题的总数目
    @Select("select count(*) from question where courseId = #{courseId}")
    int getAllToTalByCourse(String courseId);
//    具体分类获取试题
    @Select("SELECT question.examId,course.className,question.courseId,question.title,question.type,question.optionList,question.answer,question.`explain` from question,course where question.courseId = course.classId and question.courseId = #{courseId} and question.type = #{questionType} limit #{totalPage},#{size}")
    List<QuestionCourseInfoVo> getQuestionByCourseIdAndQuestionType(String courseId,String questionType,int totalPage, int size);
//    具体数目
    @Select("select count(*) from question where courseId = #{courseId} and question.type = #{questionType}")
    int getAllTotalByCourseAndQuestion(String courseId,String questionType);
//    根据Id删除试题
    @Delete("delete from question where examId = #{examId}")
    int deleteQuestionByExamId(String examId);
//    添加试题
    @Insert("INSERT INTO question (examId,courseId,title,type,optionList,answer,`explain`) VALUES (#{examId},#{courseId},#{title},#{type},#{optionList},#{answer},#{explain})")
    int addQuestion(String examId,String courseId,String title,String type,String optionList,String answer,String explain);
//    修改试题
    @Update("UPDATE question set courseId = #{courseId},title=#{title},type=#{type},optionList=#{optionList},answer=#{answer},`explain`=#{explain} where examId = #{examId}")
    int updateQuestion(String examId,String courseId,String title,String type,String optionList,String answer,String explain);
//  根据课程以及类型分页获取试题
    @Select("select * from question where courseId =#{courseId} and type = #{type} limit #{totalPage},#{size}")
    List<Question> getQuestionByCourseIdAndTypeByPage(String courseId,String type,int totalPage, int size);
//    根据课程以及分页获取试题的总条数
    @Select("select count(*) from question where courseId =#{courseId} and type = #{type}")
    int getAllTotalBySelect(String courseId,String type);
}
