package com.huc.eamil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.StringUtils;

/**
 * �����ʼ�������
 * @author Administrator
 *
 */
public class MailKit {
	
	/**�����ʼ�����Э���ַ*/
	public static final String SMTP_QQ = "smtp.qq.com";
	public static final String SMTP_163 = "smtp.163.com";
	public static final String SMTP_162 = "smtp.162.com";
	public static final String SMTP_SINA = "smtp.sina.com";
	public static final String SMTP_GMAIL = "smtp.gmail.com";
	
	private static String CFG_SMTP = SMTP_QQ;
	private static String SEND_USER = "";
	private static String SEND_PASSWORD = "";
	
	/**
	 * �����ʼ�Э����˻�
	 * @param smtp  smtpЭ��
	 * @param user �������û���
	 * @param password ����������
	 */
	public static void config(String smtp,String user,String password){
		CFG_SMTP = smtp;
		SEND_USER = user;
		SEND_PASSWORD = password;
	}
	
	/**
	 * ����ģ�巢��
	 * @param mailTemplate
	 * @param subject
	 * @return
	 */
	public static boolean send(MailTemplate mailTemplate,String subject){
		if(null != mailTemplate && StringUtils.isNotBlank(subject)){
			 return sendProcess(CFG_SMTP, SEND_USER, SEND_PASSWORD, 
	                    mailTemplate.getToMail(), mailTemplate.getCcMail(), 
	                    subject, mailTemplate.toString(), mailTemplate.getFileList());
		}
		return false;
		
	}

	/**
	 * �����ʼ�
	 * @param toMail �ռ��˵�ַ
	 * @param subject ��������
	 * @param content ��������
	 * @return
	 */
	public static boolean send(String toMail,String subject,String content){
		return sendProcess(CFG_SMTP, SEND_USER, SEND_PASSWORD, toMail, null, subject, content, null);
	}
	
	/***
	 * �����ʼ������͸���
	 * @param toMail �ռ��˵�ַ
	 * @param subject ���ͱ���
	 * @param content ��������
	 * @param files �����б�
 	 * @return
	 */
	public static boolean send(String toMail,String subject,String content,String ...files){
		return sendProcess(CFG_SMTP,SEND_USER,SEND_PASSWORD,toMail,null,subject,content,Arrays.asList(files));
	}
	
	/***
	 * �����ʼ������͸���
	 * @param toMail �ռ��˵�ַ
	 * @param subject ���ͱ���
	 * @param content ��������
	 * @param files �����б�
 	 * @return
	 */
	public static boolean send(String toMail,String subject,String content,List<String> files){
		return sendProcess(CFG_SMTP, SEND_USER, SEND_PASSWORD, toMail, null, subject, content, files);
	}
	
	/***
	 * �����ʼ����ҳ���
	 * @param toMail �ռ��˵�ַ
	 * @param ccMail ���͵�ַ
	 * @param subject ��������
	 * @param content ��������
	 * @return
	 */
	public static boolean sendAndcc(String toMail,String ccMail,String subject,String content){
		return sendProcess(CFG_SMTP, SEND_USER, SEND_PASSWORD, toMail, ccMail, subject, content, null);
	}
	
	/**
	 * �����ʼ������ͣ�������
	 * @param toMail �ռ��˵�ַ
	 * @param ccMail ���͵�ַ
	 * @param subject 
	 * @param content
	 * @param files
	 * @return
	 */
	public static boolean sendAndcc(String toMail,String ccMail,String subject,String content,String ...files){
		return sendProcess(CFG_SMTP, SEND_USER, SEND_PASSWORD, toMail, ccMail, subject, content, Arrays.asList(files));
	}
	
	/**
	 * �����ʼ������ͣ�������
	 * @param toMail �ռ��˵�ַ
	 * @param ccMail ���͵�ַ
	 * @param subject 
	 * @param content
	 * @param files
	 * @return
	 */
	public static boolean sendAndcc(String toMail,String ccMail,String subject,String content,List<String> files){
		return sendProcess(CFG_SMTP, SEND_USER, SEND_PASSWORD, toMail, ccMail, subject, content, files);
	}
	
	/**
	 * �����ʼ�
	 * @param smtp Э��
	 * @param fromAddress �����˵�ַ
	 * @param fromPass ����������
	 * @param toMailList �ռ��˵�ַ
	 * @param ccAddress ���͵�ַ
	 * @param subject �ʼ�����
	 * @param content �ʼ�����
	 * @param fileList ����
	 * @return
	 */
	public static boolean sendProcess(String smtp,String fromAddress,String fromPass
			,String toMailList,String ccAddress,String subject,String content,List<String> fileList){
		try {
			EmailHandle emailHandle = new EmailHandle(smtp);
			emailHandle.setFrom(fromAddress);
			emailHandle.setNeedAuth(true);
			emailHandle.setSubject(subject);
			emailHandle.setBody(content);
			emailHandle.setToList(toMailList);
			
			/**��ӳ���**/
			if(StringUtils.isNotEmpty(ccAddress)){
				emailHandle.setCopyToList(ccAddress);
			}
			
			emailHandle.setFrom(fromAddress);
			emailHandle.setNamePass(fromAddress, fromPass);
			
			if(null != fileList && fileList.size()>0){
				/**�����ļ�·��**/
				for (String file : fileList) {
					emailHandle.addFileAffix(file);
				}
			}
			return emailHandle.sendEmail();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	/*******************************************************�첽���ͣ�S***************************************************/
	/**
	 * ����ģ�巢��
	 * @param mailTemplate
	 * @param subject
	 * @return
	 */
	public static void asynSend(MailTemplate mailTemplate,String subject){
		if(null != mailTemplate && StringUtils.isNotBlank(subject)){
			asynSend(CFG_SMTP, SEND_USER, SEND_PASSWORD, 
	                    mailTemplate.getToMail(), mailTemplate.getCcMail(), 
	                    subject, mailTemplate.toString(), mailTemplate.getFileList());
		}
	}
	
	/**
	 * �첽�����ʼ�
	 * @param toMail �ռ��˵�ַ
	 * @param subject �ʼ�����
	 * @param content �ʼ�����
	 */
	public static void asynSend(final String toMail,final String subject,final String content){
		asynSend(CFG_SMTP, SEND_USER, SEND_PASSWORD, toMail, null, subject, content, null);
	}
	
	/***
	 * �첽�����ʼ�������
	 * @param toMail �ռ��˵�ַ
	 * @param ccMail �����˵�ַ
	 * @param subject �ʼ�����
	 * @param content �ʼ�����
	 */
	public static void asynSend(final String toMail,final String ccMail,final String subject,final String content){
		asynSend(CFG_SMTP, SEND_USER, SEND_PASSWORD, toMail, ccMail, subject, content, null);
	}
	
	/***
	 * �첽�����ʼ������ͣ����͸���
	 * @param toMail �ռ��˵�ַ
	 * @param subject �ʼ�����
	 * @param content �ʼ�����
	 * @param files ����
	 */
	public static void asynSend(final String toMail,final String ccMail,final String subject,final String content,final String ...files){
		asynSend(CFG_SMTP, SEND_USER, SEND_PASSWORD, toMail, ccMail, subject, content, Arrays.asList(files));
	}
	
	/***
	 * �첽�����ʼ������ͣ����͸���
	 * @param toMail �ռ��˵�ַ
	 * @param ccMail ����
	 * @param subject �ʼ�����
	 * @param content �ʼ�����
	 * @param files ����
	 */
	public static void asynSend(final String toMail,final String ccMail,final String subject,final String content,final List<String> files){
		asynSend(CFG_SMTP, SEND_USER, SEND_PASSWORD, toMail, ccMail, subject, content, files);
	}
	
	/**
     * �����ʼ�
     * @param smtp        �ʼ�Э��
     * @param fromAddress �����˵�ַ
     * @param fromPass    ����������
     * @param toAddress   �ռ��˵�ַ
     * @param ccAdress    �����˵�ַ
     * @param subject     ��������
     * @param content     ��������
     * @throws Exception
     */
    public static boolean asynSend(final String smtp,final String fromAddress,final String fromPass,final String toAddress,
            final String ccAdress,final String subject, final String content,final List<String> fileList){
        Boolean flag = Boolean.FALSE;
        FutureTask<Boolean> futureTask = null;
        ExecutorService excutorService = Executors.newCachedThreadPool();
        // ִ������
        futureTask = new FutureTask<Boolean>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                EmailHandle emailHandle = new EmailHandle(smtp);
                emailHandle.setFrom(fromAddress);
                emailHandle.setNeedAuth(true);
                emailHandle.setSubject(subject);
                emailHandle.setBody(content);
                emailHandle.setToList(toAddress);
                /**��ӳ���**/
                if(StringUtils.isNotEmpty(ccAdress)){
                    emailHandle.setCopyToList(ccAdress);
                }
                emailHandle.setFrom(fromAddress);
                emailHandle.setNamePass(fromAddress, fromPass);
                if(null != fileList && fileList.size() > 0){
                    /** �����ļ�·�� **/
                    for(String file : fileList){
                        emailHandle.addFileAffix(file);
                    }
                }
                return emailHandle.sendEmail();
            }
        });
        excutorService.submit(futureTask);
         
        try {
            // ����û��ʱ˵�����ͳɹ�
            flag = futureTask.get(5L, TimeUnit.SECONDS); 
        } catch (InterruptedException e) {
            futureTask.cancel(true);
            e.printStackTrace();
        } catch (ExecutionException e) {
            futureTask.cancel(true);
            e.printStackTrace();
        } catch (TimeoutException e) {
            futureTask.cancel(true);
            e.printStackTrace();
        } finally {
            excutorService.shutdown();
        }
        return flag;
    }
}
