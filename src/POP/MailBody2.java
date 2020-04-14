package POP;

/**
 * 
 * @author guoyuhang
 * @Description:接收方的邮件信息
 */

public class MailBody2 {
	//信发件人
	public String sendUser;
	//密码
	public String sendUserPwd;
	//收件人
	public String receiveUser;
	//密码
	public String receiveUserPwd;
	//是否全部
	public boolean isAllRece;
	//第几封
	public String fromNum;
	//到第几封
	public String lastNum;
	
	public String getSenderUser() {
		return sendUser;
	}
	
	public String getSendUserPwd() {
		return sendUserPwd;
	}
	
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser; 
	}
	
	public void setSendUserPwd(String sendUserPwd) {
		this.sendUserPwd = sendUserPwd;
	}
	
	public String getReveiverUser() {
		return receiveUser;
	}
	
	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser; 
	}
	
	public String getReceiveUserPwd() {
		return receiveUserPwd;
	}
	
	public void setReceiveUserPwd(String receiveUserPwd) {
		this.receiveUserPwd = receiveUserPwd;
	}

	
	public boolean isAllRece() {
		return isAllRece;
	}
	
	public void setAllRece(boolean isAllRece) {
		this.isAllRece = isAllRece;
	}
	
	public String getFromNum() {
		return fromNum;
	}

	public void setFromNum(String fromNum) {
		this.fromNum = fromNum;
	}

	public String getLastNum() {
		return lastNum;
	}

	public void setLastNum(String lastNum) {
		this.lastNum = lastNum;
	}
	
	@Override
	public String toString(){
		return  "MailBody2 [sendUser=" + sendUser + ", sendUserPwd=" + sendUserPwd + ", isAllRece=" + isAllRece
				+ ", fromNum=" + fromNum + ", lastNum=" + lastNum + "]";
	}
	

}
