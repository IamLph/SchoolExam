package com.lph.exam_web.vo;

import lombok.Data;
/*考试结果*/
@Data
public class ExamResultVo {
    private String noticeId;
    private String className;
    private String type;
    private String teacherName;
    private String isPublish;
}
