package com.lph.exam_web.service;

import com.lph.exam_web.common.Result;

public interface QuestionService {
//    获取所有试题
    Result<Object> getAllQuestion();
//    根据类型以及问题获取试题 用户收藏的题
    Result<Object> getQuestionByUserIdAndType(String userId,String type);
//    根据课程以及类型获取试题
    Result<Object> getQuestionByCourseIdAndType(String courseId,String type);
//    断是否有过收藏,并且修改收藏属性,试题关系
    Result<Object> getUserFavor(String userId,String examId,String userFavor);
//    综合练习，随机10题
    Result<Object> getQuestionByCourseIdType4(String courseId,int num);
//    错题的添加与修改
    Result<Object> updateUserWrong(String userId,String examId,String isWrong);
//    获取收藏试题
    Result<Object> getFavorQuestion(String userId);
//    获取错题集
    Result<Object> getWrongQuestion(String userId);
//    获取考试试题
    Result<Object> getExamPaper(String noticeId);
//    分页获取试题
    Result<Object> getQuestionByPage(int totalPage);
//    根据ID获取试题
    Result<Object> getQuestionById(String examId);
//    分类获取试题
    Result<Object> getQuestionByType(String courseId,String questionType,int totalPage);
//    根据id删除试题
    Result<Object> deleteQuestionByExamId(String examId);
//    添加试题
    Result<Object> addQuestion(String courseId,String title,String type,String optionList,String answer,String explain);
//    修改试题
    Result<Object> updateQuestion(String examId,String courseId,String title,String type,String optionList,String answer,String explain);
//    根据课程以及类型分页获取试题
    Result<Object> getQuestionByCourseIdAndTypeByPage(String courseId,String type,int totalPage);
}
