package com.lph.exam_web.service;

import com.lph.exam_web.common.Result;

public interface VideoService {
//    获取所有视频
    Result<Object> getAllVideo();
//    根据课程id获取视频
    Result<Object> getVideoByClassId(String classId);
//    根据学生ID获取学生的视频信息
    Result<Object> getVideoInfoByUserId(String userId,String classId);
//    根据用户观看修改视频信息
    Result<Object> updateVideoInfoByUserIdAndVideoId(String userId,String videoId,String initTime,String remainTime,String videoIsOk);
//    根据Id删除视频
    Result<Object> deleteVideoById(String id);
}
