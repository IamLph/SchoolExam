package com.lph.exam_web.util;

import com.lph.exam_web.vo.dateInfo;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class StampToDate {

    public static dateInfo getDate(Date date1, Date date2){
        dateInfo dateInfo3 = new dateInfo();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("HH:mm");
        dateInfo3.setTime(simpleDateFormat1.format(date1));
        dateInfo3.setStartTime(simpleDateFormat2.format(date1));
        dateInfo3.setEndTime(simpleDateFormat3.format(date2));
        return dateInfo3;
    }

    public static int getSurTime(Date date) throws Exception{
        Date noeDate = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.parse(sdf.format(date));
        noeDate = sdf.parse(sdf.format(noeDate));

        Calendar cal = Calendar.getInstance();
        cal.setTime(noeDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        if(Integer.parseInt(String.valueOf(between_days))<=0){
            return 0;
        }else{
            return Integer.parseInt(String.valueOf(between_days));
        }
    }
}
