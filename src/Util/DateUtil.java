package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author     : xsy
 * @description: 日期工具类
 * @date       : 2020/3/31
 */
public class DateUtil {
    /**
     * 转换时间格式
     * @author  csy
     * @param   date
     * @return  字符串
     */
    public static String dateFormat(Date date){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}
