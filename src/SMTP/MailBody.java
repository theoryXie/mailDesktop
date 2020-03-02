package SMTP;

import java.util.Date;
import java.util.List;

/**
 *
 * @author     : xsy
 * @description: 邮件系统主体类
 * @date       : 2020/2/29
 */


public class MailBody {
    // 发件人
    private String sendUser;
    // 密码(授权码)
    private String sendUserPwd;
    // 收件人
    private List<String> recUsers;
    // 主题
    private String mailSubject;
    // 附件地址
    private List<String> filesAddr;
    // 正文
    private String mainText;
    // 发送时间
    private Date sendDate;

    public MailBody() {
    }

    public MailBody(String sendUser, String sendUserPwd, List<String> recUsers, String mailSubject, List<String> filesAddr, String mainText, Date sendDate) {
        this.sendUser = sendUser;
        this.sendUserPwd = sendUserPwd;
        this.recUsers = recUsers;
        this.mailSubject = mailSubject;
        this.filesAddr = filesAddr;
        this.mainText = mainText;
        this.sendDate = sendDate;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getSendUserPwd() {
        return sendUserPwd;
    }

    public void setSendUserPwd(String sendUserPwd) {
        this.sendUserPwd = sendUserPwd;
    }

    public List<String> getRecUsers() {
        return recUsers;
    }

    public void setRecUsers(List<String> recUsers) {
        this.recUsers = recUsers;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public List<String> getFilesAddr() {
        return filesAddr;
    }

    public void setFilesAddr(List<String> filesAddr) {
        this.filesAddr = filesAddr;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
