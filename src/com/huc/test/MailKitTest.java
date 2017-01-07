package com.huc.test;

import com.huc.eamil.MailKit;

public class MailKitTest {
	public static void main(String[] args) {
//		send();
		asynSend();
//		asynSend(0);
	}
	
	//·¢ËÍÓÊ¼þ
	public static void send(){
		MailKit.config(MailKit.SMTP_163, "huc8023cheny@163.com", "253678Hu");
		boolean flag = MailKit.send("459382234@qq.com", "²âÊÔ·¢ËÍÓÊ¼þ", "²âÊÔ·¢ËÍÓÊ¼þ");
		System.out.println(flag);
	}
	
	//Òì²½·¢ËÍÓÊ¼þ
	public static void asynSend(){
		MailKit.config(MailKit.SMTP_163, "huc8023cheny@163.com", "253678Hu");
		final String toMail = "459382234@qq.com";
		final String subject = "Òì²½²âÊÔ·¢ËÍÓÊ¼þ";
		final String content = "²âÊÔ·¢ËÍÓÊ¼þ";
		MailKit.asynSend(toMail, subject, content);
	}
	
	//Òì²½·¢ËÍÓÊ¼þ,²¢³­ËÍ
	public static void asynSend(int i){
		MailKit.config(MailKit.SMTP_163, "huc8023cheny@163.com", "253678Hu");
		final String toMail = "459382234@qq.com";//ÊÕ¼þÈË
		final String ccMail = "544592787@qq.com";//³­ËÍ
		final String subject = "Òì²½²âÊÔ·¢ËÍÓÊ¼þ";
		final String content = "²âÊÔ·¢ËÍÓÊ¼þ";
		MailKit.asynSend(toMail, ccMail, subject, content);
	}
}
