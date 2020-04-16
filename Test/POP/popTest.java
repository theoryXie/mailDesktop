package POP;

import SMTP.MailBody;
import org.junit.Test;

public class popTest {


    //测试pop3
    @Test
    public void testPop(){
        ReceiveController rec = new ReceiveController();
        PopMailServer ps = new PopMailServer("pop.qq.com","110");
        MailBody mb2 = new MailBody();
        mb2.setSendUser("827041735@qq.com");
        mb2.setSendUserPwd("---");
        PopResult result = rec.receiveMail(ps,mb2);
        for(String res : result.getMailString()){
            System.out.println(res);
            break;
        }
    }
}
