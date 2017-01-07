package com.huc.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class StringUtils {
	private static final Log log = LogFactory.getLog(StringUtils.class);
	
	public static final String ENCODING_GBK = "GBK";
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ENCODING_ISO88591 = "ISO-8859-1";
	public static final String CHARSETNAME_DEFAULT = ENCODING_UTF8;	

	public static String encode(String str) {

		return encode(str, CHARSETNAME_DEFAULT);
	}

	public static String decode(String str) {

		return decode(str, CHARSETNAME_DEFAULT);
	}
	
	public static String nullToStrTrim(String str) {
		if (str == null) {
			str = "";
		}
		return str.trim();
	}

	public static int nullToIntZero(String str) {
		if (ValidatorUtils.isNull(str)) {
			str = "0";
		}
		return Integer.valueOf(str.trim(), 10);
	}
	
	public static String byte2hex(byte[] b) {

		String str = "";
		String stmp = "";

		int length = b.length;

		for (int n = 0; n < length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				str += "0";
			}
			str += stmp;
		}

		return str.toLowerCase();
	}
	


	public static String encode(String str, String enc) {

		String strEncode = "";

		try {
			if (str != null)
				strEncode = URLEncoder.encode(str, enc);
		} catch (UnsupportedEncodingException e) {
			log.error(CommonUtils.getTraceInfo() +" str:"+str+"  enc:"+enc+"\t"+ nullToStrTrim(e.getMessage()));
		}

		return strEncode;
	}

	public static String decode(String str, String enc) {

		String strDecode = "";

		try {
			if (str != null)
				strDecode = URLDecoder.decode(str, enc);
		} catch (UnsupportedEncodingException e) {
			log.error(CommonUtils.getTraceInfo() +" str:"+str+"  enc:"+enc+"\t"+ nullToStrTrim(e.getMessage()));
		}

		return strDecode;
	}
	
	public static String subString(String str,String begin,String end) {
		int t = str.indexOf(begin);
		String x = str.substring(t+begin.length());
		x = x.substring(0,x.indexOf(end));
		return x;
	}
	
	public static String arrayToString(Object[] object){
		StringBuffer buffer = new StringBuffer("[") ;
		for(Object obj : object){
			buffer.append(String.valueOf(obj)).append(",") ;
		}
		buffer.delete(buffer.length()-1, buffer.length()) ;
		buffer.append("]") ;
		return buffer.toString() ;
	}
	
}
