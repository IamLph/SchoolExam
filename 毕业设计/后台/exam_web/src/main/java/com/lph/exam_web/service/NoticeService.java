package com.lph.exam_web.service;

import com.lph.exam_web.common.Result;
import com.lph.exam_web.pojo.ExamPaper;
import com.lph.exam_web.vo.SetScoreVo;

import java.util.List;

public interface NoticeService {
//    获取所有通知
    Result<Object> getAllNotice();
//    根据通知Id获取通知信息
    Result<Object> getNoticeById(String noticeId);
//    添加通知
    Result<Object> addNotice(String type, String startTime, String endTime, String teacherId,String courseId);
//    添加试题
    Result<Object> addExamPaper(List<ExamPaper> list);
//    分页获取通知
    Result<Object> getNoticeByPage(int totalPage);
//    根据课程或者通知编号分页获取通知信息
    Result<Object> getNoticeByCourseIdAndNoticeIdByPage(String courseId,String noticeId,int totalPage);
//    修改通知状态
    Result<Object> updateNoticeStatus(int type,String noticeId);
//    获取已经结束的考试结果
    Result<Object> getExamResult();
//    根据试卷Id获取考试记录
    Result<Object> getExamByNoticeId(String noticeId);
//    修改学生分数
    Result<Object> setScoreByUserIdAndNoticeId(List<SetScoreVo> list);
//    修改分数发布状态
    Result<Object> setScorePublic(String noticeId);
//    搜索结果
    Result<Object> getExamResultByNoticeId(String noticeId);
}
