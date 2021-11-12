package com.lph.exam_web.vo;

import lombok.Data;

@Data
public class ScoreVo {
    private String userId;
    private String name;
    private int score;
    private int live;
    private int finalscore;
}
