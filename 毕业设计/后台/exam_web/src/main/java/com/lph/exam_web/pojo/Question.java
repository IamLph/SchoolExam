package com.lph.exam_web.pojo;

import lombok.Data;

@Data
public class Question {
    private String examId;
    private String courseId;
    private String title;
    private String type;
    private String optionList;
    private String answer;
    private String userAnswer;
    private String userFavor;
    private String explain;
}
