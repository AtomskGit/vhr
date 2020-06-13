package com.atomsk.vhr.model;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/11
 */
public class MailConstants {
    public static final Integer DELIVERING = 0;//消息投递中
    public static final Integer SUCCESS = 1;//消息投递成功
    public static final Integer FAILURE = 2;//消息投递失败
    public static final Integer MAX_TRY_COUNT = 3;//最大重试次数
    public static final Integer MSG_TIMEOUT = 1;//消息超时时间
    public static final String MAIL_QUEUE_NAME = "atomsk.mail.queue";
    public static final String MAIL_EXCHANGE_NAME = "atomsk.mail.exchange";
    public static final String MAIL_ROUTING_KEY_NAME = "atomsk.mail.routing.key";
}
