package com.lph.exam_web.service.impl;

import com.lph.exam_web.common.Code;
import com.lph.exam_web.common.Result;
import com.lph.exam_web.mapper.NoticeMapper;
import com.lph.exam_web.pojo.ExamPaper;
import com.lph.exam_web.pojo.Notice;
import com.lph.exam_web.service.NoticeService;
import com.lph.exam_web.util.RandomCount;
import com.lph.exam_web.util.StampToDate;
import com.lph.exam_web.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class NoticeServiceImpl implements NoticeService {

    @Autowired(required = false)
    private NoticeMapper noticeMapper;

    @Override
    public Result<Object> getAllNotice() {
        List<Notice> list = noticeMapper.getAllNotice();
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

//    根据Id获取通知
    @Override
    public Result<Object> getNoticeById(String noticeId) {
        Notice notice = noticeMapper.getNoticeById(noticeId);
        if(notice == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),notice);
    }

    @Override
    public Result<Object> addNotice(String type, String startTime, String endTime, String teacherId,String courseId) {
//        随机获取通知Id
        RandomCount randomCount = new RandomCount();
        String noticeId = randomCount.getNoticeId();
        int a = noticeMapper.addNotice(noticeId,type,startTime,endTime,teacherId,courseId);
        if(a == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"添加失败");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),noticeId);
        }
    }

    @Override
    public Result<Object> addExamPaper(List<ExamPaper> list) {
        int result = noticeMapper.addExamPaper(list);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"添加失败");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"添加了"+result+"条");
        }
    }

//    分页获取试题
    @Override
    public Result<Object> getNoticeByPage(int totalPage) {
        int size = 5;
        totalPage = (totalPage -1)*size;
        int allTotal = noticeMapper.getAllTotal();
//        System.out.println("总条数：====>>"+allTotal);
        if(allTotal%size == 0){
            allTotal = (allTotal/size);
        }else{
            allTotal = (allTotal/size)+1;
        }
        List<NoticeVo> list = noticeMapper.getNoticeByPage(totalPage,size);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<NoticeStringVo> list2 = new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            //将时间转化为类似 2020-02-13 16:01:30 格式的字符串
            NoticeStringVo stringVo = new NoticeStringVo();
            stringVo.setNoticeId(list.get(i).getNoticeId());
            stringVo.setClassName(list.get(i).getClassName());
            stringVo.setType(list.get(i).getType());
            stringVo.setStartTime(sdf.format(list.get(i).getStartTime()));
            stringVo.setEndTime(sdf.format(list.get(i).getEndTime()));
            stringVo.setTeacherName(list.get(i).getTeacherName());
            stringVo.setIsPublish(list.get(i).getIsPublish());
            list2.add(stringVo);
        }
        if(list2 == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            NoticeAndTotalVo t = new NoticeAndTotalVo();
            t.setNotice(list2);
            t.setAllTotal(allTotal);
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
        }
    }
//编号 课程查询通知
    @Override
    public Result<Object> getNoticeByCourseIdAndNoticeIdByPage(String courseId, String noticeId,int totalPage) {
        int size = 5;
        totalPage = (totalPage -1)*size;
//时间转化器
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<NoticeStringVo> list2 = new ArrayList<>();
//        判断传递的是哪个参数
        if(noticeId == null || noticeId == ""){
            //根据课程Id
            List<NoticeVo> list = noticeMapper.getNoticeByCourseId(courseId,totalPage,size);
            int allTotal = list.size();
            if(allTotal%size == 0){
                allTotal = (allTotal/size);
            }else{
                allTotal = (allTotal/size)+1;
            }
            for(int i=0;i<list.size();i++)
            {
                //将时间转化为类似 2020-02-13 16:01:30 格式的字符串
                NoticeStringVo stringVo = new NoticeStringVo();
                stringVo.setNoticeId(list.get(i).getNoticeId());
                stringVo.setClassName(list.get(i).getClassName());
                stringVo.setType(list.get(i).getType());
                stringVo.setStartTime(sdf.format(list.get(i).getStartTime()));
                stringVo.setEndTime(sdf.format(list.get(i).getEndTime()));
                stringVo.setTeacherName(list.get(i).getTeacherName());
                stringVo.setIsPublish(list.get(i).getIsPublish());
                list2.add(stringVo);
            }
            if(list2 == null){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
            }else{
                NoticeAndTotalVo t = new NoticeAndTotalVo();
                t.setNotice(list2);
                t.setAllTotal(allTotal);
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
            }
        }else{
//            if(courseId==null&&noticeId!=null || courseId==""&&noticeId!="")
            System.out.println(noticeId);
//            根据通知Id
            List<NoticeVo> list = noticeMapper.getNoticeVoByNoticeId(noticeId,totalPage,size);
//            System.out.println(list);
            for(int i=0;i<list.size();i++)
            {
                //将时间转化为类似 2020-02-13 16:01:30 格式的字符串
                NoticeStringVo stringVo = new NoticeStringVo();
                stringVo.setNoticeId(list.get(i).getNoticeId());
                stringVo.setClassName(list.get(i).getClassName());
                stringVo.setType(list.get(i).getType());
                stringVo.setStartTime(sdf.format(list.get(i).getStartTime()));
                stringVo.setEndTime(sdf.format(list.get(i).getEndTime()));
                stringVo.setTeacherName(list.get(i).getTeacherName());
                stringVo.setIsPublish(list.get(i).getIsPublish());
                list2.add(stringVo);
            }
            if(list2 == null){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
            }else{
                NoticeAndTotalVo t = new NoticeAndTotalVo();
                t.setNotice(list2);
                t.setAllTotal(1);
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
            }
        }
    }

    @Override
    public Result<Object> updateNoticeStatus(int type,String noticeId) {
        if(type == 4){
            int result = noticeMapper.deleteNoticeById(noticeId);
            if (result == 0) {
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"删除失败");
            }else{
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"删除成功");
            }
        }else{
            int result = noticeMapper.updateStatus(type+"",noticeId);
            if (result == 0) {
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
            }else{
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改成功");
            }
        }
    }

    @Override
    public Result<Object> getExamResult() {
        List<ExamResultVo> list = noticeMapper.getExamResult();
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
        }
    }

    @Override
    public Result<Object> getExamByNoticeId(String noticeId) {
        List<ScoreVo> list = noticeMapper.getExamByNoticeId(noticeId);
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
        }
    }

    @Override
    public Result<Object> setScoreByUserIdAndNoticeId(List<SetScoreVo> list) {
        int result = noticeMapper.setScoreByUserIdAndNoticeId(list);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改了"+result+"条");
        }
    }

    @Override
    public Result<Object> setScorePublic(String noticeId) {
        int result = noticeMapper.setScorePublic(noticeId);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"公布失败");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"公布成功");
        }
    }

    @Override
    public Result<Object> getExamResultByNoticeId(String noticeId) {
        List<ExamResultVo> list = noticeMapper.getExamResultByNoticeId(noticeId);
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
        }
    }
}
