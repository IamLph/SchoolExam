package com.lph.exam_web.vo;

import com.lph.exam_web.pojo.User;
import lombok.Data;

import java.util.List;

@Data
public class UserAndTotalVo {
    private List<User> users;
    private int allTotal;
}
