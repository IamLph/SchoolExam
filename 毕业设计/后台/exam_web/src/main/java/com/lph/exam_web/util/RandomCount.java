package com.lph.exam_web.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomCount {
    // 生成5位随机数id
    public String getCourseId(){
        Random random = new Random();
        String result="";
        for(int i=0;i<5;i++){
            //首字母不能为0
            result += (random.nextInt(9)+1);
        }
        return result;
    }
    // 生成6位随机数id
    public String getRandomId(){
        Random random = new Random();
        String result="";
        for(int i=0;i<6;i++){
            //首字母不能为0
            result += (random.nextInt(9)+1);
        }
        return result;
    }
//    获取7位试题账号
    public String getExamId(){
        Random random = new Random();
        String result="";
        for(int i=0;i<7;i++){
            //首字母不能为0
            result += (random.nextInt(9)+1);
        }
        return result;
    }
//    获取8位考试编号
    public String getNoticeId(){
        Random random = new Random();
        String result="";
        for(int i=0;i<8;i++){
            //首字母不能为0
            result += (random.nextInt(9)+1);
        }
        return result;
    }
}
