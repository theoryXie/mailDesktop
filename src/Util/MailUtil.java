package Util;

import java.util.regex.Pattern;

/**
 *
 * @author     : xsy
 * @description: 公用邮件工具类
 * @date       : 2020/2/29
 */
public class MailUtil {

    /**
     *
     * 验证邮箱地址格式是否正确
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



    /**
     *
     * 验证邮箱用户邮箱和密码是否正确
     *
     * @author  csy
     * @param  user -- 用户名
     * @param  pwd  -- 密码
     */
    public static boolean checkAccount(String user, String pwd){
        //TODO 验证用户名和密码
        return false;
    }
}
