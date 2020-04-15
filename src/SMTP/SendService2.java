package SMTP;

import java.io.*;
import java.net.Socket;
import java.util.Date;

import Util.DateUtil;
import Util.MailUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * @author : xsy
 * @description: java原生实现smtp
 * @date : 2020/3/31
 */
public class SendService2 {

    Socket client;//客户端socket
    BufferedReader bf;//输入流
    DataOutputStream dos;//输出流
    //跨平台换行符
    String newLine = System.getProperty("line.separator");


    /**
     * 初始化socket、bf、dos
     *
     * @param mailServer -- 服务器配置
     * @author xsy
     */
    private void init(MailServer mailServer) throws IOException {
        String url = mailServer.getSmtpUrl();
        int port = Integer.parseInt(mailServer.getSmtpPort());
        //建立socket
        client = new Socket(url, port);
        //建立输入流
        bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //建立输出流
        dos = new DataOutputStream(client.getOutputStream());
    }


    /**
     * 发送邮件
     *
     * @param mailBody   -- 邮件主体
     * @param mailServer -- 服务器配置
     * @return
     * @author xsy
     */
    public String sendMail(MailServer mailServer, MailBody mailBody) throws IOException {
        //服务器配置
        init(mailServer);


        /*=====================建立连接===========================================*/
        String result = bf.readLine();
        if (!result.startsWith("220")) {
            return "建立连接失败！" + result;
        }

        /* =====================发送打招呼指令 EHLO hello==========================*/
        result = sendCommand("EHLO " + mailServer.getSmtpUrl() + newLine);
        //检测返回的消息是否以250开头
        if (!result.startsWith("250")) {
            return "第一次握手失败！" + result;
        }
        for (int i = 0; i < 7; i++)
            bf.readLine();


        /* ======================发送权限认证指令 auth login====================== */
        result = sendCommand("auth login" + newLine);
        if (!result.startsWith("334")) {
            return "权限认证指令有误！" + result;
        }


        /*======================验证用户名和密码===================================*/
        result = sendCommand(Base64.encode(mailBody.getSendUser().getBytes()) + newLine);
        if (!result.startsWith("334")) {
            return "用户名邮箱错误！" + result;
        }
        result = sendCommand(Base64.encode(mailBody.getSendUserPwd().getBytes()) + newLine);
        if (!result.startsWith("235")) {
            return "密码错误！" + result;
        }

        /*=============================发送邮件===================================*/
        // Mail From:<...>
        result = sendCommand("MAIL FROM: <" + mailBody.getSendUser() + ">" + newLine);
        if (!result.startsWith("250")) {
            return "邮件发送地址错误" + result;
        }
        //RCPT TO:<...>
        for(String recAddr : mailBody.getRecUsers()){
            result = sendCommand("RCPT TO: <"+recAddr+">"+newLine);
            if(!result.startsWith("250")){
                return "邮件接收地址错误" + recAddr;
            }
        }

        // DATA
        result = sendCommand("DATA" + newLine);
        if (!result.startsWith("354")) {
            return "DATA指令出错" + result;
        }
        //邮件主体
        String sb = "";
        if (null == mailBody.getFilesAddr())
            sb = sendHtml(mailBody);//发送超文本
        else
            sb = sendHtmlAndFile(mailBody);//发送超文本和附件

        result = sendCommand(sb);
        System.out.println(sb);
        if (!result.startsWith("250")) {
            return "邮件主体发生错误" + result;
        }

        /* ======================quit===============================*/
        result = sendCommand("QUIT" + newLine);
        if (!result.startsWith("221")) {
            return "会话退出错误" + result;
        }

        dos.close();
        bf.close();
        client.close();
        return "发送成功！";
    }


    /**
     * 发送smtp指令
     *
     * @param msg -- 发送的指令
     * @return 返回服务器响应信息
     */
    private String sendCommand(String msg) throws IOException {
        dos.writeBytes(msg);
        dos.flush();
        return bf.readLine();
    }


    /**
     * 发送html文本
     *
     * @param mailBody -- 邮件体
     * @return telnet代码
     * @author xsy
     */
    private String sendHtml(MailBody mailBody) {
        return "From: " + mailBody.getSendUser() + newLine +
                "To: " + mailBody.getRecUsers() + newLine +
                "MIME-Version: 1.0" + newLine +
                "Subject: =?utf-8?b?" + Base64.encode(mailBody.getMailSubject().getBytes()) + "?=" + newLine +
                "Content-Type:multipart/mixed;boundary=\"a\"" + newLine +
                "Date: " + DateUtil.dateFormat(new Date()) + newLine +
                newLine +
                "--a" + newLine +
                "Content-Type: text/html; charset=\"utf-8\"" + newLine +
                "Content-Transfer-Encoding: base64" + newLine +
                newLine +
                Base64.encode(mailBody.getMainText().getBytes()) + newLine +
                newLine +
                "--a" + newLine +
//                "Content-Transfer-Encoding:base64"+newLine+
//                "Content-type:application/octet-stream; name=\"test\""+newLine+
//                newLine+
//                Base64.encode(MailUtil.getContent(mailBody.getFilesAddr().get(0)))+newLine+//附件base64
//                newLine+
                "." + newLine;
    }

    /**
     * 发送html文本和附件
     *
     * @param mailBody -- 邮件体
     * @return telnet指令代码
     * @author xsy
     */
    private String sendHtmlAndFile(MailBody mailBody) throws IOException {
        StringBuilder sb = new StringBuilder();
        String init = "From: " + mailBody.getSendUser() + newLine +
                "To: " + mailBody.getRecUsers() + newLine +
                "MIME-Version: 1.0" + newLine +
                "Subject: =?utf-8?b?" + Base64.encode(mailBody.getMailSubject().getBytes()) + "?=" + newLine +
                "Content-Type:multipart/mixed;boundary=\"a\"" + newLine +
                "Date: " + DateUtil.dateFormat(new Date()) + newLine +
                newLine +
                "--a" + newLine +
                "Content-Type: text/html; charset=\"utf-8\"" + newLine +
                "Content-Transfer-Encoding: base64" + newLine +
                newLine +
                Base64.encode(mailBody.getMainText().getBytes()) + newLine +
                newLine;
        sb.append(init);
        for (String addr : mailBody.getFilesAddr()) {
            String[] splits = addr.split("\\\\");
            String fileName = splits[splits.length - 1];
            String file_code = // 附件代码
                    "--a" + newLine +
                            "Content-Transfer-Encoding:base64" + newLine +
                            "Content-type:application/octet-stream; name=\"" + fileName + "\"" + newLine +
                            newLine +
                            Base64.encode(MailUtil.getContent(addr)) + newLine +
                            newLine;//附件base64
            sb.append(file_code);
        }
        sb.append(newLine).append(".").append(newLine);
        return sb.toString();
    }
}
