package com.huc.test;

import com.huc.eamil.MailKit;

public class MailKitTest {
	public static void main(String[] args) {
//		send();
		asynSend();
//		asynSend(0);
	}
	
	//�����ʼ�
	public static void send(){
		MailKit.config(MailKit.SMTP_163, "huc8023cheny@163.com", "253678Hu");
		boolean flag = MailKit.send("459382234@qq.com", "���Է����ʼ�", "���Է����ʼ�");
		System.out.println(flag);
	}
	
	//�첽�����ʼ�
	public static void asynSend(){
		MailKit.config(MailKit.SMTP_163, "huc8023cheny@163.com", "253678Hu");
		final String toMail = "459382234@qq.com";
		final String subject = "�첽���Է����ʼ�";
		final String content = "���Է����ʼ�";
		MailKit.asynSend(toMail, subject, content);
	}
	
	//�첽�����ʼ�,������
	public static void asynSend(int i){
		MailKit.config(MailKit.SMTP_163, "huc8023cheny@163.com", "253678Hu");
		final String toMail = "459382234@qq.com";//�ռ���
		final String ccMail = "544592787@qq.com";//����
		final String subject = "�첽���Է����ʼ�";
		final String content = "���Է����ʼ�";
		MailKit.asynSend(toMail, ccMail, subject, content);
	}
}
