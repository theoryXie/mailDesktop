package Util;

import SMTP.DeliveredState;

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
     * @author  xsy
     * @param  state -- 邮件发送后的状态
     * @return 状态对应的字符串
     */
    public static String judgeSMTPState(DeliveredState state){
        String ans = "";
        switch (state){
            case INITIAL:
                ans = "邮件尚在初始化！";
                break;
            case MESSAGE_DELIVERED:
                ans = "邮件发送成功！";
                break;
            case MESSAGE_NOT_DELIVERED:
                ans = "邮件发送失败！";
                break;
            case MESSAGE_PARTIALLY_DELIVERED:
                ans = "邮件部分发送成功！";
                break;
            default:
                ans = "异常！！！";
        }
        return ans;
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
