package SMTP;

/**
 *
 * @author     : xsy
 * @description: 发送邮件后返回的状态
 * @date       : 2020/3/9
 */
public enum DeliveredState {
    //初始状态
    INITIAL,
    //邮件发送出去了
    MESSAGE_DELIVERED,
    //邮件没有发送出去
    MESSAGE_NOT_DELIVERED,
    //邮件部分发送出去了
    MESSAGE_PARTIALLY_DELIVERED,
}
