package com.lph.exam_web.service.impl;

import com.lph.exam_web.common.Code;
import com.lph.exam_web.common.Result;
import com.lph.exam_web.mapper.QuestionMapper;
import com.lph.exam_web.pojo.Question;
import com.lph.exam_web.service.QuestionService;
import com.lph.exam_web.util.RandomCount;
import com.lph.exam_web.vo.QuestionAndTotalVo;
import com.lph.exam_web.vo.QuestionByPageAndTotalVo;
import com.lph.exam_web.vo.QuestionCourseInfoVo;
import com.lph.exam_web.vo.UserQuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class QuestionServiceImpl implements QuestionService {
    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Override
    public Result<Object> getAllQuestion() {
        List<Question> list = questionMapper.getAllQuestion();
        if (list == null) {
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> getQuestionByUserIdAndType(String userId, String type) {
        List<UserQuestionVo> list = questionMapper.getQuestionByUserIdAndType(userId, type);
//        System.out.println(list);
        if (list.size() == 0) {
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> getQuestionByCourseIdAndType(String courseId, String type) {
        List<Question> list = questionMapper.getQuestionByCourseIdAndType(courseId, type);
        if (list.size() == 0) {
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> getUserFavor(String userId, String examId,String userFavor) {
        int result = questionMapper.getUserAndExamRelation(userId, examId);
//        System.out.println(result);
//        如果没有记录的话，就去添加关系字段
        if(result == 0){
            int a = questionMapper.addFavor(userId, examId, userFavor);
            if(a == 0){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"添加失败");
            }
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"添加成功");
        }else{
//            如果有关系的话，就去修改字段
            int b = questionMapper.updateFavor(userId, examId, userFavor);
            if(b == 0){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
            }
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改成功");
        }
    }

    @Override
    public Result<Object> getQuestionByCourseIdType4(String courseId,int num) {
        List<Question> list = questionMapper.getQuestionByCourseIdType4(courseId,num);
        if(list.size() == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> updateUserWrong(String userId, String examId, String isWrong) {
        int result = questionMapper.getUserAndExamRelation(userId, examId);
        //        如果没有记录的话，就去添加关系字段
        if(result == 0){
            int a = questionMapper.insertUserWrong(userId, examId);
            if(a == 0){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"添加失败");
            }
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"添加成功");
        }else{
//            如果有关系的话，就去修改字段
            int b = questionMapper.updateUserWrong(userId, examId, isWrong);
            if(b == 0){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
            }
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改成功");
        }
    }

    @Override
    public Result<Object> getFavorQuestion(String userId) {
        List<UserQuestionVo> list = questionMapper.getFavorQuestion(userId);
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
        }
    }

    @Override
    public Result<Object> getWrongQuestion(String userId) {
        List<UserQuestionVo> list = questionMapper.getWrongQuestion(userId);
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
        }
    }

    @Override
    public Result<Object> getExamPaper(String noticeId) {
        List<Question> list = questionMapper.getExamPaper(noticeId);
        if(list.size() == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
        }
    }

    @Override
    public Result<Object> getQuestionByPage(int totalPage) {
        int size = 5;
        totalPage = (totalPage -1)*size;
        int allTotal = questionMapper.getAllTotal();
        if(allTotal%size == 0){
            allTotal = (allTotal/size);
        }else{
            allTotal = (allTotal/size)+1;
        }
        List<QuestionCourseInfoVo> list = questionMapper.getQuestionByPage(totalPage,size);
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            QuestionAndTotalVo t = new QuestionAndTotalVo();
            t.setQuestions(list);
            t.setAllTotal(allTotal);
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
        }
    }

    @Override
    public Result<Object> getQuestionById(String examId) {
        List<QuestionCourseInfoVo> list = questionMapper.getQuestionById(examId);
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            QuestionAndTotalVo t = new QuestionAndTotalVo();
            t.setQuestions(list);
            t.setAllTotal(1);
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
        }
    }

    @Override
    public Result<Object> getQuestionByType(String courseId, String questionType,int totalPage) {
        int size = 5;
        int allTotal;
        totalPage = (totalPage -1)*size;
        QuestionAndTotalVo t = new QuestionAndTotalVo();
        if(courseId == null && questionType != null || courseId == "" && questionType != ""){
//            只根据题目类型选择
//            System.out.println("只根据题目类型选择:"+questionType);
            allTotal = questionMapper.getAllTotalByQuestion(questionType);
            if(allTotal%size == 0){
                allTotal = (allTotal/size);
            }else{
                allTotal = (allTotal/size)+1;
            }
            List<QuestionCourseInfoVo> list = questionMapper.getQuestionByQuestionType(questionType,totalPage,size);
            if(list == null){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
            }else{
                t.setQuestions(list);
                t.setAllTotal(allTotal);
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
            }
        }else if(courseId != null && questionType == null || courseId != "" && questionType == ""){
//            只根据课程选择
//            System.out.println("只根据课程选择:"+courseId);
            allTotal = questionMapper.getAllToTalByCourse(courseId);
            if(allTotal%size == 0){
                allTotal = (allTotal/size);
            }else{
                allTotal = (allTotal/size)+1;
            }
            List<QuestionCourseInfoVo> list = questionMapper.getQuestionByCourseId(courseId,totalPage,size);
            if(list == null){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
            }else{
                t.setQuestions(list);
                t.setAllTotal(allTotal);
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
            }
        }else if(courseId == null && questionType == null || courseId == "" && questionType == ""){
//            全部都要
//            System.out.println("全部都要");
            allTotal = questionMapper.getAllTotal();
            if(allTotal%size == 0){
                allTotal = (allTotal/size);
            }else{
                allTotal = (allTotal/size)+1;
            }
            List<QuestionCourseInfoVo> list = questionMapper.getQuestionByPage(totalPage,size);
            if(list == null){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
            }else{
                t.setQuestions(list);
                t.setAllTotal(allTotal);
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
            }
        }else if(courseId != null && questionType != null || courseId != "" && questionType != ""){
//            具体分类选择
//            System.out.println("具体分类选择:"+courseId+":"+questionType);
            allTotal = questionMapper.getAllTotalByCourseAndQuestion(courseId,questionType);
            if(allTotal%size == 0){
                allTotal = (allTotal/size);
            }else{
                allTotal = (allTotal/size)+1;
            }
            List<QuestionCourseInfoVo> list = questionMapper.getQuestionByCourseIdAndQuestionType(courseId,questionType,totalPage,size);
            if(list == null){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
            }else{
                t.setQuestions(list);
                t.setAllTotal(allTotal);
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
            }
        }else{
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"传参异常");
        }
    }

    @Override
    public Result<Object> deleteQuestionByExamId(String examId) {
        int a = questionMapper.deleteQuestionByExamId(examId);
        if(a == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"删除失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"删除成功");
    }

    @Override
    public Result<Object> addQuestion(String courseId, String title, String type, String optionList, String answer, String explain) {
        RandomCount randomCount = new RandomCount();
        String examId = randomCount.getExamId();
        int result = questionMapper.addQuestion(examId,courseId,title,type,optionList,answer,explain);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"添加失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"添加成功");
    }

    @Override
    public Result<Object> updateQuestion(String examId, String courseId, String title, String type, String optionList, String answer, String explain) {
        int result = questionMapper.updateQuestion(examId, courseId, title, type, optionList, answer, explain);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改成功");
    }

    @Override
    public Result<Object> getQuestionByCourseIdAndTypeByPage(String courseId, String type, int totalPage) {
        int size = 5;
        totalPage = (totalPage -1)*size;
        int allTotal = questionMapper.getAllTotalBySelect(courseId, type);
        if(allTotal%size == 0){
            allTotal = (allTotal/size);
        }else{
            allTotal = (allTotal/size)+1;
        }
//        封装
        List<Question> list = questionMapper.getQuestionByCourseIdAndTypeByPage(courseId,type,totalPage,size);
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            QuestionByPageAndTotalVo t = new QuestionByPageAndTotalVo();
            t.setQuestions(list);
            t.setAllTotal(allTotal);
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
        }
    }

}
