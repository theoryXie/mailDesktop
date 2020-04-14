package POP;
/**
 * 
 * @author guoyuhang
 * @Description: 邮件服务器参数类
 */
public class PopMailServer {
	    private String popUrl;
	    private String popPort = "110"; // pop端口默认为110


	    public PopMailServer(String popUrl, String popPort) {
	        this.popUrl = popUrl;
	        this.popPort = popPort;
	    }

	    public String getPopPort() {
	        return popPort;
	    }

	    public void setPopPort(String popPort) {
	        this.popPort = popPort;
	    }

	    public String getPopUrl() {
	        return popUrl;
	    }

	    public void setPopUrl(String popUrl) {
	        this.popUrl = popUrl;
	    }

	    @Override
	    public String toString() {
	        return "PopMailServer{" +
	                "popUrl='" + popUrl + '\'' +
	                ", popPort='" + popPort + '\'' +
	                '}';
	    }
}
