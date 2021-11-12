package com.lph.exam_web.vo;

import lombok.Data;

import java.util.List;

@Data
public class QuestionAndTotalVo {
    private List<QuestionCourseInfoVo> questions;
    private int allTotal;
}
