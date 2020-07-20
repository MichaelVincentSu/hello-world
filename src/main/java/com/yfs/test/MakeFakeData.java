package com.yfs.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Author 于凡粟
 * @Date 2020/5/22 2:52 下午
 */
public class MakeFakeData {
    public static void main(String[] args) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = df.parse("2020-05-01 00:00:00");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long tim = cal.getTimeInMillis();
        long add = 60 * 1000;
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            System.out.println("insert into one (insert_time,one,two) values ('" + stampToDate(tim+add*i) + "','"+(1+random.nextInt(5))+"','"+random.nextInt(1000)+"');");
        }
//        for (int i = 0; i < 50; i++) {
//            System.out.println("insert into two (insert_time,one,two) values ('" + stampToDate(tim+add*i) + "','"+(1+random.nextInt(11))+"','"+random.nextInt(1000)+"');");
//        }
//        for (int i = 0; i < 100; i++) {
//            System.out.println("insert into three (insert_time,one,two,three,four,five) values ('" + stampToDate(tim+add*i) + "','"+(1+random.nextInt(2))+"','"+(1+random.nextInt(3))+"','"+(30+random.nextInt(10))+"','"+(60+random.nextInt(10))+"','10001');");
//        }
//        for (int i = 0; i < 100; i++) {
//            System.out.println("insert into five (insert_time,one,two,three,four,five,six,seven) values ('" + stampToDate(tim+add*i) + "','"
//                    +(1+random.nextInt(2))+"','"
//                    +(300+random.nextInt(10))+"','1','"
//                    +(30+random.nextInt(10))+"','"
//                    +(60+random.nextInt(10))+"','"
//                    +(100+random.nextInt(10))+"','"
//                    +"10001');");
//        }
//        for (int i = 0; i < 100; i++) {
//            System.out.println("insert into six (insert_time,one,two,three,four) values ('" + stampToDate(tim+add*i) + "','"
//                    +(1+random.nextInt(10))+"','"
//                    +(1+random.nextInt(3))+"','"
//                    +(30+random.nextInt(10))+"','"
//                    +"10001');");
//        }
    }

    public static String stampToDate(long lt){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
