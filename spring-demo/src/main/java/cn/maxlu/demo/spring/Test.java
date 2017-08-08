package cn.maxlu.demo.spring;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luwei on 2017/7/12.
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date _fromDate = sf.parse("2017-01-04 00:00:00");
        Date _toDate = sf.parse("2017-01-03 00:00:00");
        int days = daysBetween(_fromDate, _toDate);

        System.out.println(days);
    }

    public static int daysBetween(Date fromDate, Date toDate) {
        if (fromDate == null || toDate == null) {
            throw new RuntimeException("时间为空");
        }

        Date _fromDate = dropTime(fromDate);
        Date _toDate = dropTime(toDate);

        return ((int) (_fromDate.getTime() / 1000) - (int) (_toDate.getTime() / 1000)) / 3600 / 24;
    }

    /**
     * 去掉时分秒
     */
    private static Date dropTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(df.format(date));
        } catch (ParseException e) {
            throw new RuntimeException("格式化错误");
        }
    }

    private static void ff() {
        InputStream resourceAsStream = Test.class.getClassLoader().getResourceAsStream("/cn/maxlu/demo/spring/dd.xml");
        System.out.println(resourceAsStream);
    }

    private static void dd() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = addDays(simpleDateFormat.parse("2017-10-17 01:13:35"), 20);
        while (true) {
            System.out.println(simpleDateFormat.format(date));
        }
    }

    private static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    private static void S() {
        BigInteger a = new BigInteger("0");
        BigInteger b = a.setBit(1);
        boolean has = b.testBit(1);
        System.out.println(has);

        boolean has2 = b.testBit(2);
        System.out.println(has2);

        System.out.println(a.toString());
        System.out.println(b.toString());
    }
}
