package POP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import SMTP.MailBody;
import Util.DateUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * @author guoyuhang
 * @Description:java原生实现pop3
 */
public class ReceiveService {
    private Socket client; //客户端socket
    private BufferedReader bf; //输入流
    private DataOutputStream dos; //输出流

    //跨平台换行符
    String newLine = System.getProperty("line.separator");
    
    /**
     * @param @param        popMailserver
     * @param @throws       IOException
     * @param popMailserver
     * @return void
     * @throws IOException
     * @Description: popmailServer -- pop服务器配置
     */
    private void init(PopMailServer popMailserver) throws IOException {
        String url = popMailserver.getPopUrl();
        int port = Integer.parseInt(popMailserver.getPopPort());
        //建立socket
        client = new Socket(url, port);
        //建立输入流
        bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //建立输出流
        dos = new DataOutputStream(client.getOutputStream());

    }

    public PopResult receiveMail(PopMailServer popMailserver, MailBody mailbody2) throws IOException {
        //pop服务器配置
        init(popMailserver);

        /*=====================建立连接===========================================*/
        String result = bf.readLine();
        if (!result.startsWith("+OK")) {
            return new PopResult("建立连接失败！" + result);
        }

        /*=====================登陆pop3服务器===========================================*/
        result = sendCommand("user " + mailbody2.getSendUser() + newLine);
        if (!result.startsWith("+OK")) {
            return new PopResult("邮箱用户名错误！" + result);
        }
        result = sendCommand("pass " + mailbody2.getPwd() + newLine);
        if (!result.startsWith("+OK")) {
            return new PopResult("邮箱密码错误！" + result);
        }

        /*=====================查看邮件数量及大小===========================================*/
        result = sendCommand("STAT" + newLine);
        if (!result.startsWith("+OK")) {
            return new PopResult("STAT指令错误！" + result);
        }
        String[] splits = result.split(" ");//+OK {num} {size}
        int mailNum = Integer.parseInt(splits[1]);//邮件数量
        int allSize = Integer.parseInt(splits[2]);//邮件大小


        /*=====================读取邮件===========================================*/
        List<String> mailString = new ArrayList<>();//邮件字符串集合
        for(int i = 1;i <= mailNum; i++){
            //获取邮件内容
            String ans = sendCommand("RETR "+i+newLine);
            if(!ans.startsWith("+OK")) {
                return new PopResult("读取第"+i+"封邮件出错");
            }
            mailString.add(getContent());
        }
        
        return new PopResult(mailString, mailNum, allSize, "读取成功！");
    }

    /**
     * @param @return
     * @return 详细信件内容
     * @Description:完整读取信件内容
     */
    private String getContent() throws IOException {
        StringBuilder content = new StringBuilder(bf.readLine());
        String temp = bf.readLine();
        while (!temp.contentEquals(".")) {
            content.append(temp);
            temp = bf.readLine();
        }
        return content.toString();
    }

    /**
     * @param @param  msg 发送指令
     * @param @return 返回服务器响应信息
     * @param @throws IOException
     * @return
     * @throws IOException
     * @Description: 发送pop3指令
     */
    private String sendCommand(String msg) throws IOException {
    	System.out.println(msg);
        dos.writeBytes(msg);
        dos.flush();
        return bf.readLine();
    }
    
    /**
     * @param   id --邮件编号
     * @return
     * @throws IOException
     * @Description: 删除邮件（标识）
     */
    public boolean deleteMail(int id) throws IOException {
    	/*=====================删除特定邮件===========================================*/
        String result = sendCommand("dele "+id + newLine);
        if (!result.startsWith("+OK")) {
            return false;
        }
        destroy();
        return true;
	}

    /**
	 * 断开连接
     */
    private void destroy() throws IOException {
    	sendCommand("quit" + newLine);
    	dos.close();
        bf.close();
        client.close();
	}
}