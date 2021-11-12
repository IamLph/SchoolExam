package com.lph.exam_web.pojo;

import lombok.Data;

@Data
public class Course {
    private String classId;
    private String classImgUrl;
    private String className;
    private String classTeacherName;
    private int site;
}
