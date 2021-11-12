package com.lph.exam_web.vo;

import lombok.Data;

@Data
public class QuestionCourseInfoVo {
    private String examId;
    private String className;
    private String courseId;
    private String title;
    private String type;
    private String optionList;
    private String answer;
    private String explain;
}
