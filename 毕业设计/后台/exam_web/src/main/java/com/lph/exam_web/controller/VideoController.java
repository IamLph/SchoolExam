package com.lph.exam_web.controller;

import com.lph.exam_web.common.Result;
import com.lph.exam_web.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@ResponseBody
@Controller
@Api(tags = "视频接口")
@RequestMapping("api/Video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ApiOperation("获取所有视频")
    @GetMapping("/getAllVideo")
    public Result<Object> getAllVideo(){
        return videoService.getAllVideo();
    }

    @ApiOperation("根据课程号获取视频")
    @GetMapping("/getVideoByClassId")
    public Result<Object> getVideoByClassId(@RequestParam(value = "classId")String classId){
        return videoService.getVideoByClassId(classId);
    }

    @ApiOperation("根据用户id获取视频学习信息")
    @GetMapping("/getVideoInfoByUserId")
    public Result<Object> getVideoInfoByUserId(@RequestParam(value = "userId")String userId,
                                               @RequestParam(value = "classId")String classId){
        return videoService.getVideoInfoByUserId(userId,classId);
    }

    @ApiOperation("根据用户id以及视频信息，修改播放进度")
    @PostMapping("/updateVideoInfoByUserIdAndVideoId")
    public Result<Object> updateVideoInfoByUserIdAndVideoId(@RequestParam(value = "userId")String userId,
                                                @RequestParam(value = "videoId")String videoId,
                                                @RequestParam(value = "initTime")String initTime,
                                                @RequestParam(value = "remainTime")String remainTime,
                                                @RequestParam(value = "videoIsOk")String videoIsOk){
        return videoService.updateVideoInfoByUserIdAndVideoId(userId, videoId, initTime,remainTime, videoIsOk);
    }

    @ApiOperation("根据视频Id删除视频")
    @PostMapping("/deleteVideoById")
    public Result<Object> deleteVideoById(@RequestParam(value = "id")String id){
        return videoService.deleteVideoById(id);
    }
}
