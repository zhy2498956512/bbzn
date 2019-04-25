package com.example.bbzn.util;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Properties;

public class MailboxDemo {

    static private final String comMailbox = "beibo@vormor.cn";//企业邮箱
    static private final String comMailboxPassword = "123456789";//企业邮箱密码

    public static void setComMailbox(String mail,String nuberm) throws Exception {
        String arr[] ={mail};//接收邮箱
        String subject = "绑定邮箱";//邮件主题
        String name = "贝铂智能";//发件人昵称展示
        String host = "smtp.ym.163.com";//163企业邮箱smtp
        StringBuffer content = new StringBuffer();//内容
        content.append("<html><head></head>");
        content.append("<body><div><h2>邮箱验证</h2>亲爱的用户:您好!感谢您使用VTrans。您正在进行邮箱验证，请在验证码输入框中输入此次验证码（5分钟内有效)：<a href='javascript:;' >").append(nuberm).append("</a>以完成验证。如非本人操作，请忽略此邮件,由此给您带来的不便请谅解!</div>");
        content.append("<div><span style ='float: right;'>贝铂智能</span></div>");
        content.append("</body></html>");
        sendHtmlMail(name,arr,subject,content.toString(),host,comMailbox,comMailboxPassword);
    }

    /**
     * 企业邮箱 发送html邮件（方法1）
     *
     * @param from     发件人
     * @param to       收件人
     * @param subject  邮件主题
     * @param text     邮件内容
     * @param host     邮件服务器类型(请传入smtp类型的邮件服务器)
     * @param username 登录用户名
     * @param password 登录密码
     * @throws MessagingException
     */
    public static void sendHtmlMail(String from, String[] to, String subject, String text, String host, String username, String password) throws Exception {
        //设置服务器验证信息
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.timeout", "465");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();// SSL加密
        sf.setTrustAllHosts(true); // 设置信任所有的主机
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        //设置邮件内容
        JavaMailSenderImpl javaMailSend = new JavaMailSenderImpl();
        MimeMessage message = javaMailSend.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
        String nick = MimeUtility.encodeText(from);//设置昵称
        messageHelper.setFrom(new InternetAddress(nick + " <" + username + ">"));// 邮件发送者
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(text, true);

        //设置邮件服务器登录信息
        javaMailSend.setHost(host);
        javaMailSend.setUsername(username);
        javaMailSend.setPassword(password);
        javaMailSend.setJavaMailProperties(prop);

        javaMailSend.send(message);
    }



}
