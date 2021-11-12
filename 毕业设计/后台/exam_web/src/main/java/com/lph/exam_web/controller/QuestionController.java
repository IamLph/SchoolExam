package com.lph.exam_web.controller;

import com.lph.exam_web.common.Result;
import com.lph.exam_web.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@ResponseBody
@Controller
@Api(tags = "试题接口")
@RequestMapping("api/Question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @ApiOperation("获取所有试题")
    @GetMapping("/getAllQuestion")
    public Result<Object> getAllQuestion(){
        return questionService.getAllQuestion();
    }
    @ApiOperation("根据课程以及类型获取试题")
    @GetMapping("/getQuestionByCourseIdAndType")
    public Result<Object> getQuestionByCourseIdAndType(@RequestParam(value = "courseId")String courseId,
                                                       @RequestParam(value = "type")String type){
        return questionService.getQuestionByCourseIdAndType(courseId, type);
    }

    @ApiOperation("根据课用户ID以及类型获取用户收藏的试题")
    @GetMapping("/getQuestionByUserIdAndType")
    public Result<Object> getQuestionByUserIdAndType(@RequestParam(value = "userId")String userId,
                                                     @RequestParam(value = "type")String type){
        return questionService.getQuestionByUserIdAndType(userId, type);
    }

    @ApiOperation("用户的收藏与取消")
    @GetMapping("/updateFavor")
    public Result<Object> updateFavor(@RequestParam(value = "userId")String userId,
                                     @RequestParam(value = "examId")String examId,
                                      @RequestParam(value = "userFavor")String userFavor){
        return questionService.getUserFavor(userId, examId, userFavor);
    }
    @ApiOperation("根据课程Id获取综合试题")
    @GetMapping("/getQuestionByCourseIdType4")
    public Result<Object> getQuestionByCourseIdType4(@RequestParam(value = "courseId")String courseId,
                                                     @RequestParam(value = "num")int num){
        return questionService.getQuestionByCourseIdType4(courseId,num);
    }

    /**
     *
     * @param userId
     * @param examId
     * @param isWrong 斩或者不斩 true,false
     * @return
     */
    @ApiOperation("添加或修改用户的错题")
    @PostMapping("/updateUserWrong")
    public Result<Object> updateUserWrong(@RequestParam(value = "userId")String userId,
                                          @RequestParam(value = "examId")String examId,
                                          @RequestParam(value = "isWrong")String isWrong){
        return questionService.updateUserWrong(userId, examId, isWrong);
    }
    @ApiOperation("获取用户收藏的试题")
    @GetMapping("/getFavorQuestion")
    public Result<Object> getUserFavor(@RequestParam(value = "userId")String userId){
        return questionService.getFavorQuestion(userId);
    }
    @ApiOperation("获取用户错题集")
    @GetMapping("/getWrongQuestion")
    public Result<Object> getUserWrong(@RequestParam(value = "userId")String userId){
        return questionService.getWrongQuestion(userId);
    }
    @ApiOperation("获取试卷")
    @GetMapping("/getExamPaper")
    public Result<Object> getExamPaper(@RequestParam(value = "noticeId")String noticeId){
        return questionService.getExamPaper(noticeId);
    }

    @ApiOperation("分页获取试题")
    @GetMapping("/getQuestionByPage")
    public Result<Object> getQuestionByPage(@RequestParam(value = "totalPage")int totalPage){
        return questionService.getQuestionByPage(totalPage);
    }

    @ApiOperation("根据ID获取试题")
    @GetMapping("/getQuestionById")
    public Result<Object> getQuestionById(@RequestParam(value = "examId")String examId){
        return questionService.getQuestionById(examId);
    }

    @ApiOperation("根据分类取试题")
    @GetMapping("/getQuestionByType")
    public Result<Object> getQuestionByType(@RequestParam(value = "courseId",required = false)String courseId,
                                            @RequestParam(value = "questionType",required = false)String questionType,
                                            @RequestParam(value = "totalPage")int totalPage){
        return questionService.getQuestionByType(courseId, questionType,totalPage);
    }

    @ApiOperation("根据Id删除试题")
    @PostMapping("/deleteQuestionByExamId")
    public Result<Object> deleteQuestionByExamId(@RequestParam(value = "examId")String examId){
        return questionService.deleteQuestionByExamId(examId);
    }

    @ApiOperation("添加试题")
    @PostMapping("/addQuestion")
    public Result<Object> addQuestion(@RequestParam(value = "courseId") String courseId,
                                      @RequestParam(value = "title") String title,
                                      @RequestParam(value = "type") String type,
                                      @RequestParam(value = "optionList") String optionList,
                                      @RequestParam(value = "answer") String answer,
                                      @RequestParam(value = "explain") String explain){
        return questionService.addQuestion(courseId, title, type, optionList, answer, explain);
    }

    @ApiOperation("修改试题")
    @PostMapping("/updateQuestion")
    public Result<Object> updateQuestion(@RequestParam(value = "examId")String examId,
                                         @RequestParam(value = "courseId") String courseId,
                                          @RequestParam(value = "title") String title,
                                          @RequestParam(value = "type") String type,
                                          @RequestParam(value = "optionList") String optionList,
                                          @RequestParam(value = "answer") String answer,
                                          @RequestParam(value = "explain") String explain){
        return questionService.updateQuestion(examId, courseId, title, type, optionList, answer, explain);
    }
    @ApiOperation("根据课程及分类分页获取试题")
    @GetMapping("/getQuestionByCourseIdAndTypeByPage")
    public Result<Object> getQuestionByCourseIdAndTypeByPage(@RequestParam(value = "courseId") String courseId,
                                         @RequestParam(value = "type") String type,
                                         @RequestParam(value = "totalPage") int totalPage){
        return questionService.getQuestionByCourseIdAndTypeByPage(courseId, type, totalPage);
    }


}
