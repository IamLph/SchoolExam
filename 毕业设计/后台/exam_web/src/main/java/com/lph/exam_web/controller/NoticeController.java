package com.lph.exam_web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lph.exam_web.common.Result;
import com.lph.exam_web.pojo.ExamPaper;
import com.lph.exam_web.service.NoticeService;
import com.lph.exam_web.vo.SetScoreVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@Controller
@Api(tags = "通知接口")
@RequestMapping("api/Notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @ApiOperation("获取所有通知信息")
    @GetMapping("/getAllNotice")
    public Result<Object> getAllNotice(){
        return noticeService.getAllNotice();
    }

    @ApiOperation("获取通知ID获取通知信息")
    @GetMapping("/getNoticeById")
    public Result<Object> getNoticeById(@RequestParam(value = "noticeId")String noticeId){
        return noticeService.getNoticeById(noticeId);
    }

    @ApiOperation("添加通知信息")
    @PostMapping("/addNotice")
    public Result<Object> addNotice(@RequestParam(value = "type")String type,
                                    @RequestParam(value = "startTime")String startTimeD,
                                    @RequestParam(value = "endTime")String endTimeD,
                                    @RequestParam(value = "teacherId")String teacherId,
                                    @RequestParam(value = "courseId")String courseId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l_start = Long.parseLong(startTimeD);
        long l_end = Long.parseLong(endTimeD);
        String startTime = sdf.format(l_start);
        String endTime = sdf.format(l_end);
        return noticeService.addNotice(type, startTime, endTime, teacherId,courseId);
    }
//    添加试卷试题
    @ApiOperation("批量添加试题(生成试卷)")
    @PostMapping("/addExamPaper")
    public Result<Object> addExamPaper(@RequestParam(value = "list")String list){
        List<ExamPaper> lists = new ArrayList<>();
        JSONArray jsonArray = JSONObject.parseArray(list);
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = JSONObject.parseObject(jsonArray.getString(i));
            ExamPaper examPaper = new ExamPaper();
            examPaper.setNoticeId((String) jsonObject.get("noticeId"));
            examPaper.setQuestionId((String) jsonObject.get("questionId"));
            lists.add(examPaper);
        }
//        System.out.println(lists);
        return noticeService.addExamPaper(lists);
    }

//    分页获取通知
    @ApiOperation("分页获取考试通知")
    @GetMapping("/getNoticeByTotal")
    public Result<Object> getNoticeByTotal(@RequestParam(value = "totalPage")int totalPage){
        return noticeService.getNoticeByPage(totalPage);
    }
//    根据编号或者课程号获取通知
    @ApiOperation("根据编号或者课程号获取通知")
    @GetMapping("/getNoticeByCourseIdAndNoticeIdByPage")
    public Result<Object> getNoticeByCourseIdAndNoticeIdByPage(@RequestParam(value = "totalPage")int totalPage,
                                                               @RequestParam(value = "courseId",required = false)String courseId,
                                                               @RequestParam(value = "noticeId",required = false)String noticeId){
        return noticeService.getNoticeByCourseIdAndNoticeIdByPage(courseId, noticeId, totalPage);
    }

    @ApiOperation("根据Id修改通知状态")
    @PostMapping("/updateNoticeInfoById")
    public Result<Object> updateNoticeInfo(@RequestParam(value = "type")int type,
                                           @RequestParam(value = "noticeId")String noticeId){
        return noticeService.updateNoticeStatus(type, noticeId);
    }

//    获取考试结果
    @ApiOperation("获取考试结果")
    @GetMapping("/getExamResult")
    public Result<Object> getExamResult(){
        return noticeService.getExamResult();
    }

    @ApiOperation("根据通知Id获取考试情况")
    @GetMapping("/getExamByNoticeId")
    public Result<Object> getExamByNoticeId(@RequestParam(value = "noticeId")String noticeId){
        return noticeService.getExamByNoticeId(noticeId);
    }
    //    修改用户的成绩
    @ApiOperation("批量添加试题(生成试卷)")
    @PostMapping("/setScoreByUserIdAndNoticeId")
    public Result<Object> setScoreByUserIdAndNoticeId(@RequestParam(value = "list")String list){
        List<SetScoreVo> lists = new ArrayList<>();
        JSONArray jsonArray = JSONObject.parseArray(list);
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = JSONObject.parseObject(jsonArray.getString(i));
            SetScoreVo s = new SetScoreVo();
            s.setNoticeId((String) jsonObject.get("noticeId"));
            s.setFinalscore((String) jsonObject.get("finalscore"));
            s.setUserId((String) jsonObject.get("userId"));
            lists.add(s);
        }
        return noticeService.setScoreByUserIdAndNoticeId(lists);
    }
    @ApiOperation("修改分数公布状态")
    @PostMapping("/setScorePublic")
    public Result<Object> setScorePublic(@RequestParam(value = "noticeId")String noticeId){
        return noticeService.setScorePublic(noticeId);
    }
    @ApiOperation("根据通知Id获取试卷")
    @GetMapping("/getExamResultByNoticeId")
    public Result<Object> getExamResultByNoticeId(@RequestParam(value = "noticeId")String noticeId){
        return noticeService.getExamResultByNoticeId(noticeId);
    }
}


