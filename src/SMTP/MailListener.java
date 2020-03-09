package SMTP;

/**
 *
 * @author     : xsy
 * @description: smtp发送邮件事件监听类
 * @date       : 2020/3/2
 */
public class MailListener {

    //邮件发送后的状态
    private DeliveredState state = DeliveredState.INITIAL;
    /**
     * 等待发送邮件返回的消息
     *
     * @author  xsy
     */
    synchronized void waitForReady() throws InterruptedException {
        if (state == DeliveredState.INITIAL) {
            wait();//阻塞线程，等待消息
        }
    }

    synchronized void setState(DeliveredState _state){
        state = _state;
        notify();
    }


    /**
     *
     * @author  xsy
     * @return 邮件状态
     */
    synchronized DeliveredState getState(){
        return state;
    }
}
