package com.lph.exam_web.vo;

import lombok.Data;

@Data
public class UserQuestionVo {
    private String examId;
    private String courseId;
    private String title;
    private String type;
    private String optionList;
    private String answer;
    private String userAnswer;
    private String explain;
    private String userFavor;
}
