package SMTP;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.*;
import java.io.File;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author     : xsy
 * @description: 发送邮件的逻辑业务类
 * @date       : 2020/3/2
 */
public class SendService {

    //邮件发送后的状态
    private DeliveredState state = DeliveredState.INITIAL;

    /**
     * 创建邮件
     *
     * @author  xsy
     * @param  session -- 会话
     * @param  mailBody -- 邮件主体
     * @return message -- 封装好的邮件
     */
    private Message createMail(Session session, MailBody mailBody) throws Exception{
            Message mail = new MimeMessage(session);
            //发件人地址
            Address from = new InternetAddress(mailBody.getSendUser());
            mail.setFrom(from);
            //收件人地址（多个）
            int rec_size = mailBody.getRecUsers().size();//收件人个数
            Address[] recs = new InternetAddress[rec_size];
            for(int i = 0; i < rec_size; i++){
                recs[i] = new InternetAddress(mailBody.getRecUsers().get(i));
            }
            mail.setRecipients(Message.RecipientType.TO, recs);
            //设置邮件主题
            mail.setSubject(mailBody.getMailSubject());
            //设置邮件内容
            Multipart mainPart = new MimeMultipart("mixed");
            BodyPart html = new MimeBodyPart();
            MimeBodyPart attch = new MimeBodyPart();
            html.setContent(mailBody.getMainText(), "text/html; charset=UTF-8");
            mainPart.addBodyPart(html);
            //添加附件
            List<String> filesAddr = mailBody.getFilesAddr();
            if (filesAddr != null && filesAddr.size() > 0) {
                for (String fileAddr : filesAddr) {
                    attch = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(new File(fileAddr));
                    attch.setDataHandler(new DataHandler(fds));
                    attch.setFileName(MimeUtility.encodeText(fds.getName(), "UTF-8", "B"));
                    mainPart.addBodyPart(attch);
                }
            }
            mail.setContent(mainPart);
            mail.saveChanges();
            return mail;
    }

    /**
     * 发送邮件，监听消息
     *
     * @author  xsy
     * @param  server -- 会话
     * @param  mailBody -- 邮件主体
     * @return message -- 封装好的邮件
     */
    public void send(MailServer server,MailBody mailBody) throws Exception {
        Properties prop = new Properties();
        //协议(smtp)
        prop.setProperty("mail.transport.protocol","smtp");
        //服务器
        prop.setProperty("mail.host",server.getSmtpUrl());
        //端口
        prop.setProperty("mail.smtp.port",server.getSmtpPort());
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth","true");
        //用户名和密码
        prop.setProperty("username", mailBody.getSendUser());
        prop.setProperty("password", mailBody.getSendUserPwd());
        //使用SSL，企业邮箱必需！
        //开启安全协议
//        MailSSLSocketFactory sf = null;
//        try {
//            sf = new MailSSLSocketFactory();
//            sf.setTrustAllHosts(true);
//        } catch (GeneralSecurityException e1) {
//            e1.printStackTrace();
//        }
//        prop.put("mail.smtp.ssl.enable", "true");
//        assert sf != null;
//        prop.put("mail.smtp.ssl.socketFactory", sf);

        //1.根据配置创建session
        Session session = Session.getInstance(prop);
        //2.session开启debug模式，可以随时查看邮件发送状态
        session.setDebug(true);
        //3.通过session得到transport对象
        Transport tp = session.getTransport();
        //4.使用用户名和密码连接服务器
        tp.connect(mailBody.getSendUser(),mailBody.getSendUserPwd());
        //5.创建邮件
        Message message = createMail(session,mailBody);
        //6.给transport添加监听，以便知道发送结果
        tp.addTransportListener(new TransportListener() {
            public void messageDelivered(TransportEvent arg0) {
                //邮件发送成功
                state = DeliveredState.MESSAGE_DELIVERED;
                notifyAll();
            }
            public void messageNotDelivered(TransportEvent arg0) {
                //邮件发送失败
                state = DeliveredState.MESSAGE_NOT_DELIVERED;
                notifyAll();
            }
            public void messagePartiallyDelivered(TransportEvent arg0) {
                //邮件部分发送成功
                state = DeliveredState.MESSAGE_PARTIALLY_DELIVERED;
                notifyAll();
            }
        });
        //7.发送邮件到所有用户
        tp.sendMessage(message,message.getAllRecipients());
        //8.等待返回的消息
        waitForReady();
    }

    /**
     * 等待发送邮件返回的消息
     *
     * @author  xsy
     */
    synchronized void waitForReady() throws InterruptedException {
        if (state == DeliveredState.INITIAL) {
            wait();//阻塞线程，等待消息
        }
    }


    /**
     *
     * @author  xsy
     * @return 邮件状态
     */
    synchronized DeliveredState getState(){
        return state;
    }
}
