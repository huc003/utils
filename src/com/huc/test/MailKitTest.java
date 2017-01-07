package com.huc.test;

import com.huc.eamil.MailKit;

public class MailKitTest {
    public static void main(String[] args) {
		send();
//        asynSend();
//		asynSend(0);
    }

    //发送邮件
    public static void send(){
        MailKit.config(MailKit.SMTP_163, "huc8023cheny@163.com", "253678Hu");
        boolean flag = MailKit.send("459382234@qq.com", "公司错误邮件", "明天早上到公司集合，请各位注意好时间");
        System.out.println(flag);
    }

    //异步发送邮件
    public static void asynSend(){
        MailKit.config(MailKit.SMTP_163, "huc8023cheny@163.com", "253678Hu");
        final String toMail = "459382234@qq.com";
        final String subject = "异步发送邮件";
        final String content = "异步发送邮件内容";
        MailKit.asynSend(toMail, subject, content);
    }

    //异步发送邮件，并抄送
    public static void asynSend(int i){
        MailKit.config(MailKit.SMTP_163, "huc8023cheny@163.com", "253678Hu");
        final String toMail = "459382234@qq.com";//收件人地址
        final String ccMail = "544592787@qq.com";//抄送人地址
        final String subject = "异步发送邮件";
        final String content = "异步发送邮件内容";
        MailKit.asynSend(toMail, ccMail, subject, content);
    }
}
