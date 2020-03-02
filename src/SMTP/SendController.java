package SMTP;

/**
 *
 * @author     : xsy
 * @description: 与前端交互的接口类
 * @date       : 2020/2/29
 */
public class SendController {

    /**
     *
     * @author  xsy
     * @param  mailServer -- 邮件服务器配置
     * @param  mailBody -- 邮件主体配置
     * @return 发送成功 -- "发送成功"
     * @return 发送失败 -- "异常消息"
     */
    public String sendMail(MailServer mailServer, MailBody mailBody){
        try {
            //调用服务类
            SendService sendService = new SendService();
            //发送邮件
            sendService.send(mailServer,mailBody);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }




}
