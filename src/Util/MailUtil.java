package Util;

import java.util.regex.Pattern;

/**
 *
 * @author     : xsy
 * @description: 邮件工具类
 * @date       : 2020/2/29
 */
public class MailUtil {

    /**
     *
     * @author  xsy
     * @param  mails -- 邮件地址
     * @return 如果邮件地址有一个不符合要求，则返回false，否则为true
     */
    public static boolean checkMailFormat(String[] mails){
        if (mails == null) {
            return false;
        }
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        // 遍历数组
        for (String mail : mails) {
            if (!regex.matcher(mail).matches()) {
                return false;
            }
        }
        return true;
    }
}
