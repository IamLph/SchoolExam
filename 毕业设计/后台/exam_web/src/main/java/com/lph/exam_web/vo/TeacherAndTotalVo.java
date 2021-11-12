package com.lph.exam_web.vo;

import com.lph.exam_web.pojo.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class TeacherAndTotalVo {
    private List<Teacher> teachers;
    private int allTotal;

}
