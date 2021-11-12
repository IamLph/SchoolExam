package com.lph.exam_web.vo;

import lombok.Data;

@Data
public class UserVideoVo {
    private String id;
    private String classId;
    private String videoUrl;
    private String videoName;
    private String videoInfo;
    private String videoUnit;
    private String publishTime;
    private String videoImg;
    private String initTime;
    private String remainTime;
    private String videoIsOk;
}
