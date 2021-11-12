package com.lph.exam_web.vo;

import lombok.Data;

@Data
public class ExamInfoDetailVo {
    private String id;
    private String type;
    private String time;
    private String detailTime;
    private String teacherId;
    private String teacher;
    private int surTime;
}
