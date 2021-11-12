package com.lph.exam_web;

import com.lph.exam_web.vo.dateInfo;
import com.sun.jmx.snmp.Timestamp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class ExamWebApplicationTests {

    @Test
    void contextLoads() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateNowStr = sdf.format(d);
        System.out.println("格式化后的日期：" + dateNowStr);
//        System.out.println(simpleDateFormat1);
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println(System.currentTimeMillis());
//        QiniuUpload qiniuUpload = new QiniuUpload();
//        qiniuUpload.UploadPic("C:\\Users\\Lenovo\\Pictures\\001.jpg","test0001");
    }

}
