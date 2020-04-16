package POP;

import SMTP.MailBody;

import javax.swing.*;
import java.util.List;

/**
 *
 * @author     : xsy
 * @description: pop3返回值
 * @date       : 2020/4/16
 */
public class PopResult {
    private List<MailBody> mails;//接收到的所有邮件
    private List<String> mailString;//接收到的所有邮件的字符串格式
    private int allNum;//邮件数量
    private int allSize;//所有邮件大小
    private String message;//返回的消息

    public PopResult(List<String> mails, int allNum, int allSize, String message) {
        this.mailString = mails;
        this.allNum = allNum;
        this.allSize = allSize;
        this.message = message;
    }

    public PopResult(String message) {
        this.message = message;
    }

    public List<MailBody> getMails() {
        return mails;
    }

    public void setMails(List<MailBody> mails) {
        this.mails = mails;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getAllSize() {
        return allSize;
    }

    public void setAllSize(int allSize) {
        this.allSize = allSize;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getMailString() {
        return mailString;
    }

    public void setMailString(List<String> mailString) {
        this.mailString = mailString;
    }


    @Override
    public String toString() {
        return "PopResult{" +
                "mails=" + mails +
                ", mailString=" + mailString +
                ", allNum=" + allNum +
                ", allSize=" + allSize +
                ", message='" + message + '\'' +
                '}';
    }
}
