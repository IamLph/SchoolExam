package com.lph.exam_web.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private String noticeId;
    private String type;
    private Date startTime;
    private Date endTime;
    private String teacherId;
    private String isPublish;
}
