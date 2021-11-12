package com.lph.exam_web.vo;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeVo {
    private String noticeId;
    private String className;
    private String type;
    private Date startTime;
    private Date endTime;
    private String teacherName;
    private String isPublish;
}
