package com.lph.exam_web.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ExamHistory {
    private String type;
    private Date startTime;
    private int finalscore;
    private String publish;
    private String classImgUrl;
}
