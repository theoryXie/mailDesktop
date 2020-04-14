package POP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import Util.DateUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * 
 * @author guoyuhang
 * @Description:java原生实现pop3
 */
public class ReceiveService {
	private Socket client; //客户端socket
	private BufferedReader bf; //输入流
	private DataOutputStream dos; //输出流
	private Scanner socketReader;
	/**
	 * 
	 * @Description: popmailServer -- pop服务器配置
	 * @param @param popMailserver
	 * @param @throws IOException
	 * @return void
	 * @param popMailserver
	 * @throws IOException
	 */
	private void init(PopMailServer popMailserver)throws IOException{
		String url = popMailserver.getPopUrl();
		int port = Integer.parseInt(popMailserver.getPopPort());
		//建立socket
		client = new Socket(url,port);
		//建立输入流
		bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
		//建立输出流
		dos = new DataOutputStream(client.getOutputStream());
		
	}
	
	public String receiveMail(PopMailServer popMailserver, MailBody2 mailbody2)throws IOException{
		   //pop服务器配置
		   init(popMailserver);
		   
		   //扫描输入
		   Scanner clientInput = new Scanner(System.in);
		   
		   //用户输入的邮件序号
		   int num;
		   
		   //跨平台换行符
		   String newLine = System.getProperty("line.separator");
		   
		   /*=====================建立连接===========================================*/
		   String result = bf.readLine();
		   if(!result.startsWith("+OK")) {
			   return "建立连接失败！" + result;
		   }
		   
		   /*=====================登陆pop3服务器===========================================*/
		   result = sendCommand("user "+mailbody2.getReveiverUser()+newLine);
		   if(!result.startsWith("+OK")) {
			   return "邮箱用户名错误！"+result;
		   }
		   result = sendCommand("pass "+mailbody2.getReceiveUserPwd()+newLine);
		   if(!result.startsWith("+OK")) {   
			   return "邮箱密码错误！"+result; 
		   }
		  
		   /*=====================查看邮件数量及大小===========================================*/
		   result = sendCommand("STAT"+newLine);
		   if(!result.startsWith("+OK")) {
			   return "STAT指令错误！"+result;
		   }
		    
		   /*=====================查看特定邮件大小===========================================*/
		   num = clientInput.nextInt();
		   result = sendCommand("LIST"+num+newLine);
		   if(!result.startsWith("+OK")) {
			   return "LIST指令错误！"+result;
		   }
		   
		   /*=====================查看特定邮件内容===========================================*/
		   num = clientInput.nextInt();
		   String ans = sendCommand("RETR"+num);
		   getContent();
		   /*=====================删除一个邮件===========================================*/
			   
		
		    dos.close();
	        bf.close();
	        client.close();
	        return "接收成功！";
		
	}
	private String getContent() {
		String content = socketReader.nextLine();
		String temp = socketReader.nextLine();
		while(!temp.contentEquals(".")) {
			content+=temp;
			temp=socketReader.nextLine();
		}
		return content;
	}
	/**
	 * 
	 * @Description: 发送pop3指令
	 * @param @param msg 发送指令
	 * @param @return 返回服务器响应信息
	 * @param @throws IOException
	 * @return String
	 * @param msg
	 * @return
	 * @throws IOException
	 */
	private String sendCommand(String msg)throws IOException{
		dos.writeBytes(msg);
		dos.flush();
		return bf.readLine();
	}

}
