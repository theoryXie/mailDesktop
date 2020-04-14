package POP;

public class ReceiveController {
	/**
	 * 
	 * @Description:	与前端交互的接口类
	 * @param @param popmailServer
	 * @param @param mailBody2
	 * @param @return
	 * @return String
	 * @param popmailServer
	 * @param mailBody2
	 * @return
	 */
	 public String receiveMail(PopMailServer popmailServer, MailBody2 mailBody2){
	        try {
	            /*从零开始pop3*/
	            ReceiveService receiveService = new ReceiveService();
	            return receiveService.receiveMail(popmailServer, mailBody2);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return e.getMessage();
	        }
	    }
}
