package com.lph.exam_web.service.impl;

import com.lph.exam_web.common.Code;
import com.lph.exam_web.common.Result;
import com.lph.exam_web.mapper.VideoMapper;
import com.lph.exam_web.pojo.Video;
import com.lph.exam_web.service.VideoService;
import com.lph.exam_web.vo.UserVideoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Repository
public class VideoServiceImpl implements VideoService {

    @Autowired(required = false)
    private VideoMapper videoMapper;

    @Override
    public Result<Object> getAllVideo() {
        List<Video> list = videoMapper.getAllVideo();
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> getVideoByClassId(String classId) {
        List<Video> list = videoMapper.getVideoByClassId(classId);
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> getVideoInfoByUserId(String userId,String classId) {
        List<UserVideoVo> list = videoMapper.getVideoInfoByUserId(userId,classId);
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> updateVideoInfoByUserIdAndVideoId(String userId, String videoId,String initTime, String remainTime, String videoIsOk) {
        int result = videoMapper.updateUserVideo(userId, videoId, initTime,remainTime, videoIsOk);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改成功");
    }

    @Override
    public Result<Object> deleteVideoById(String id) {
        int result = videoMapper.deleteVideoById(id);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"删除失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"删除成功");
    }
}
