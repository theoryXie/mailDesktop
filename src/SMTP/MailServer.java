package SMTP;

/**
 *
 * @author     : xsy
 * @description: 邮件系统服务器参数类
 * @date       : 2020/2/29
 */
public class MailServer {

    private String smtpUrl;
    private String smtpPort = "25"; // smtp端口默认为25

    public MailServer(String smtpUrl, String smtpPort) {
        this.smtpUrl = smtpUrl;
        this.smtpPort = smtpPort;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUrl() {
        return smtpUrl;
    }

    public void setSmtpUrl(String smtpUrl) {
        this.smtpUrl = smtpUrl;
    }

    @Override
    public String toString() {
        return "MailServer{" +
                "smtpUrl='" + smtpUrl + '\'' +
                ", smtpPort='" + smtpPort + '\'' +
                '}';
    }
}
