package com.lph.exam_web.vo;

import lombok.Data;

import java.util.List;

@Data
public class NoticeAndTotalVo {
    List<NoticeStringVo> notice;
    private int allTotal;
}
