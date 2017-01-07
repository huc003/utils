package com.huc.utils;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 交易前置系统专用工具类
 * @create Aug 15, 2012 12:16:49 PM
 * @update Sep 27, 2012 3:07:09 PM
 * @author 玄玉<http://blog.csdn/net/jadyer>
 * @version v2.0
 * @history v1.7.2-->新增<code>getHexSign()</code>通过指定算法签名字符串方法
 * @history v1.7.2-->新增<code>getString()</code>字节数组转为字符串方法
 * @history v1.7.3-->修改<code>getSysJournalNo()</code>实现细节为<code>java.util.UUID.randomUUID()</code>
 * @history v1.7.4-->新增<code>getHexSign()</code>根据指定的签名密钥和算法签名Map<String,String>
 * @history v1.7.5-->新增<code>getStringSimple()</code>获取一个字符串的简明效果,返回的字符串格式类似于"abcd***hijk"
 * @history v2.0-->局部的StringBuffer一律StringBuilder之(本思路提示自坦克<captmjc@gmail.com>)
 */
public class TradePortalUtil {
	
	private static final Log log = LogFactory.getLog(TradePortalUtil.class);
	
	/**
	 * 获取系统流水号
	 * @return
	 */
	public static String getSysJournaNo(){
		return getSysJournalNo(20, true);
	}
	
	/**
	 * 获取系统流水号
	 * @param length 指定流水号长度
	 * @param isNunber 指定流水号是否全部有数字组成
	 * @return
	 */
	public static String getSysJournalNo(int length,boolean isNunber){
		//replaceAll() 之后返回的是由一个十六进制形式组成的且长度为32的字符串
		String uuid = UUID.randomUUID().toString().replace("-", "");
		if(uuid.length()>length){
			uuid = uuid.substring(0,length);
		}else if (uuid.length()<length) {
			for (int i = 0; i < length - uuid.length(); i++) {
				uuid = uuid + Math.round(Math.random()*9);
			}
		}
		if(isNunber){
			return uuid.replaceAll("a", "1").replaceAll("b", "2").replaceAll("c", "3").replaceAll("d", "4").replaceAll("e", "5").replaceAll("f", "6");
		}else {
			return uuid;
		}
	}
	
	/**
     * 判断输入的字节数组是否为空
     * @return boolean 空则返回true,非空则flase
     */
    public static boolean isEmpty(byte[] bytes){
        return null==bytes || 0==bytes.length;
    }
    
    /**
     * 判断输入的字符串参数是否为空
     * @return boolean 空则返回true,非空则flase
     */
    public static boolean isEmpty(String input) {
        return null==input || 0==input.length() || 0==input.replaceAll("\\s", "").length();
    }
	
    /**
     * HTML字符转义
     * @see 对输入参数中的敏感字符进行过滤替换,防止用户利用JavaScript等方式输入恶意代码
     * @see String input = <img src='http://t1.baidu.com/it/fm=0&gp=0.jpg'/>
     * @see HtmlUtils.htmlEscape(input);         //from spring.jar
     * @see StringEscapeUtils.escapeHtml(input); //from commons-lang.jar
     * @see 尽管Spring和Apache都提供了字符转义的方法,但Apache的StringEscapeUtils功能要更强大一些
     * @see StringEscapeUtils提供了对HTML,Java,JavaScript,SQL,XML等字符的转义和反转义
     * @see 但二者在转义HTML字符时,都不会对单引号和空格进行转义,而本方法则提供了对它们的转义
     * @return String 过滤后的字符串
     */
    public static String htmlEscape(String input){
    	if(isEmpty(input)){
    		return input;
    	}
    	input = input.replaceAll("&", "&amp;");
    	input = input.replaceAll("<", "&lt;");
    	input = input.replaceAll(">", "&gt;");
    	input = input.replaceAll(" ", "&nbsp;");
    	input = input.replaceAll("'", "&#39;");
    	input = input.replaceAll("\"", "&quot;");
    	input = input.replaceAll("/", "&frasl;");
    	input = input.replaceAll("\n", "<br/>");
    	return input;
    }
	
	public static void main(String[] args) {
		
	}
}
