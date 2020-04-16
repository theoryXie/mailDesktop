package POP;

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
	 public PopResult receiveMail(PopMailServer popmailServer, MailBody mailBody2){
	        try {
	            /*从零开始pop3*/
	            ReceiveService receiveService = new ReceiveService();
	            return receiveService.receiveMail(popmailServer, mailBody2);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
}
