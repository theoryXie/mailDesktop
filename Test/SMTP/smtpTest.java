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
        MailServer server = new MailServer("smtp.qq.com","25");
        List<String> recs = new ArrayList<>();
        recs.add("whuxcsy@163.com");
        MailBody mailBody = new MailBody("827041735@qq.com","---",recs,"hello",null,"hello xsy",null);
        controller.sendMail(server,mailBody);
    }

}
