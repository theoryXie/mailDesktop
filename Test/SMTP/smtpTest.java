package SMTP;




import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author     : xsy
 * @description: smtp测试类
 * @date       : 2020/3/2
 */
public class smtpTest {

    //测试能否发送邮件
    //因为隐私问题，邮箱授权码就用---代替
    @Test
    public void testSend(){
        SendController controller = new SendController();
        MailServer server = new MailServer("whu.edu.cn","25");
        List<String> recs = new ArrayList<>();
        List<String> files = new ArrayList<>();
        recs.add("827041735@qq.com");
        recs.add("whuxcsy@163.com");
        files.add("D:\\package\\apache-tomcat-8.5.47\\webapps\\ROOT\\jsp\\main.jsp");
        files.add("D:\\package\\apache-tomcat-8.5.47\\webapps\\ROOT\\jsp\\index.jsp");
        MailBody mailBody = new MailBody("2017302580167@whu.edu.cn","xsy171401",recs,"你好",files,"你好谢帅宇",null);
        System.out.println(controller.sendMail(server,mailBody));
    }

    //测试能否发送邮件
    //因为隐私问题，邮箱授权码就用---代替
    @Test
    public void testSend2(){
        SendController controller = new SendController();
        MailServer server = new MailServer("smtp.qq.com","25");
        List<String> recs = new ArrayList<>();
        List<String> files = new ArrayList<>();
        recs.add("827041735@qq.com");
        files.add("D:\\package\\apache-tomcat-8.5.47\\webapps\\ROOT\\jsp\\main.jsp");
        files.add("D:\\package\\apache-tomcat-8.5.47\\webapps\\ROOT\\jsp\\index.jsp");
        MailBody mailBody = new MailBody("827041735@qq.com","izaalzwhtryvbdbb",recs,"你好",files,"你好谢帅宇",null);
        System.out.println(controller.sendMail(server,mailBody));
    }

}
