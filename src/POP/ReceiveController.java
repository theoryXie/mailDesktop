package POP;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import SMTP.MailBody;

public class ReceiveController {
	/**
	 * 
	 * @Description: 与前端交互的接口类
	 * @param @param popmailServer
	 * @param @param mailBody2
	 * @param @return
	 * @return String
	 * @param popmailServer
	 * @param mailBody2
	 * @return
	 */

	//添加对收信的解码	
	 public String receiveMail(PopMailServer popmailServer, MailBody mailBody2){
	        try {
	            /*从零开始pop3*/
	            ReceiveService receiveService = new ReceiveService();
	            String result = receiveService.receiveMail(popmailServer, mailBody2).toString();
	            return Base64.decode(result).toString(); 
      
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	 }
}
