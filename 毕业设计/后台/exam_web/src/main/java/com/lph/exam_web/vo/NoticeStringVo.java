package com.lph.exam_web.vo;

import lombok.Data;

@Data
public class NoticeStringVo {
    private String noticeId;
    private String className;
    private String type;
    private String startTime;
    private String endTime;
    private String teacherName;
    private String isPublish;
}
