package com.lph.exam_web.pojo;

import lombok.Data;

@Data
public class Video {
    private String id;
    private String classId;
    private String videoUrl;
    private String videoName;
    private String videoInfo;
    private String videoUnit;
    private String publishTime;
    private String videoImg;
    private int initTime;
}
