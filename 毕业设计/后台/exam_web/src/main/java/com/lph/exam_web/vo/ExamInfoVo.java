package com.lph.exam_web.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ExamInfoVo {
    private String noticeId;
    private String type;
    private Date startTime;
    private Date endTime;
    private String teacherId;
    private String teacherName;

}
