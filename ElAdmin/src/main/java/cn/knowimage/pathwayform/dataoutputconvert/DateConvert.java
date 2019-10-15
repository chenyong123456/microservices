package cn.knowimage.pathwayform.dataoutputconvert;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相差格式转换类
 * @author lyx
 * @date 2019/09/03
 */
public class DateConvert {
    public static long dateDiff(String startTime, String endTime, String format) {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            //获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            System.out.println("diff=========>"+diff);
            day = diff/nd;//计算差多少天
            long hour = diff%nd/nh;//计算差多少小时
            long min = diff%nd%nh/nm;//计算差多少分钟
            long sec = diff%nd%nh%nm/ns;//计算差多少秒
            //输出结果
            System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    public static final Date dateAdd(Date date,int field,int amout) {
        if(date == null){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field,amout);
        return calendar.getTime();
    }


    public static void main(String[] args) {
        new DateConvert().dateDiff("2019-09-10", "2019-09-10", "yyyy-MM-dd");
    }
}