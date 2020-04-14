package SMTP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

import Util.DateUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 *
 * @author     : xsy
 * @description: java原生实现smtp
 * @date       : 2020/3/31
 */
public class SendService2 {

    Socket client;//客户端socket
    BufferedReader bf;//输入流
    DataOutputStream dos;//输出流


    /**
     *  初始化socket、bf、dos
     * @author  xsy
     * @param  mailServer -- 服务器配置
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
     * @author  xsy
     * @param  mailBody -- 邮件主体
     * @param mailServer -- 服务器配置
     * @return 
     */
    public String sendMail(MailServer mailServer, MailBody mailBody) throws IOException {
        //服务器配置
        init(mailServer);

        //跨平台换行符
        String newLine = System.getProperty("line.separator");


        /*=====================建立连接===========================================*/
        String result = bf.readLine();
        if(!result.startsWith("220")){
            return "建立连接失败！" + result;
        }

        /* =====================发送打招呼指令 EHLO hello==========================*/
        result = sendCommand("EHLO "+mailServer.getSmtpUrl()+newLine);
        //检测返回的消息是否以250开头
        if(!result.startsWith("250")){
            return "第一次握手失败！" + result;
        }
        for(int i = 0; i < 7;i++)
            bf.readLine();


        /* ======================发送权限认证指令 auth login====================== */
        result = sendCommand("auth login"+newLine);
        if(!result.startsWith("334")){
            return "权限认证指令有误！" + result;
        }


        /*======================验证用户名和密码===================================*/
        result = sendCommand(Base64.encode(mailBody.getSendUser().getBytes()) + newLine);
        if(!result.startsWith("334")){
            return "用户名邮箱错误！" + result;
        }
        result = sendCommand(Base64.encode(mailBody.getSendUserPwd().getBytes()) + newLine);
        if(!result.startsWith("235")){
            return "密码错误！" + result;
        }

        /*=============================发送邮件===================================*/
        // Mail From:<...>
        result = sendCommand("MAIL FROM: <"+mailBody.getSendUser()+">"+newLine);
        if(!result.startsWith("250")){
            return "邮件发送地址错误" + result;
        }
         //RCPT TO:<...>
//        for(String recAddr : mailBody.getRecUsers()){
//            result = sendCommand("RCPT TO: <"+mailBody.getRecUsers()+">"+newLine);
//            if(!result.startsWith("250")){
//                return "邮件接收地址错误" + recAddr;
//            }
//        }
        result = sendCommand("RCPT TO: <"+mailBody.getRecUsers().get(0)+">"+newLine);
        if(!result.startsWith("250")){
            return "邮件接收地址错误" + result;
        }

        // DATA
        result = sendCommand("DATA"+newLine);
        if(!result.startsWith("354")){
            return "DATA指令出错" + result;
        }
        //邮件主体
        StringBuilder sb = new StringBuilder();
        sb.append("Subject:").append(mailBody.getMailSubject()).append(newLine);
        sb.append("Date:").append(DateUtil.dateFormat(new Date())).append(newLine);
        sb.append("Content-Type:text/plain;charset=\"GB2312\",\"UTF-8\"").append(newLine);
        sb.append(newLine);
        sb.append(mailBody.getMainText());
        sb.append(newLine).append(".").append(newLine);

        result = sendCommand(sb.toString());
        if(!result.startsWith("250")){
            return "邮件主体发生错误" + result;
        }

        /* ======================quit===============================*/
        result = sendCommand("QUIT"+newLine);
        if(!result.startsWith("221")){
            return "会话退出错误" + result;
        }

        dos.close();
        bf.close();
        client.close();
        return "发送成功！";
    }


    /**
     * 发送smtp指令
     * @param msg -- 发送的指令
     * @return  返回服务器响应信息
     */
    private String sendCommand(String msg) throws IOException {
        dos.writeBytes(msg);
        dos.flush();
        return bf.readLine();
    }



}
