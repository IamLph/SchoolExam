package com.lph.exam_web.vo;

import com.lph.exam_web.pojo.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuestionByPageAndTotalVo {
    List<Question> questions;
    private int allTotal;
}
