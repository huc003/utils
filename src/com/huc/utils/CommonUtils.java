package com.huc.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonUtils {
	private static final Log log = LogFactory.getLog(CommonUtils.class);

	private static Random rand = null;

	private static SecureRandom secureRand = null;

	private static String localhost = "";
	
	public static final String ENCODING_GBK = "GBK";
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ENCODING_ISO88591 = "ISO-8859-1";
	public static final String CHARSETNAME_DEFAULT = ENCODING_UTF8;	

	static {
		secureRand = new SecureRandom();
		rand = new Random(secureRand.nextLong());
	}

	public static String getTraceInfo() {
		StringBuffer stringBuffer = new StringBuffer();
		StackTraceElement stackTraceElement = (new Throwable()).getStackTrace()[1];
		stringBuffer.append("(");
		stringBuffer.append("className:").append(
				stackTraceElement.getClassName());
		stringBuffer.append(";fieldName:").append(
				stackTraceElement.getFileName());
		stringBuffer.append(";methodName:").append(
				stackTraceElement.getMethodName());
		stringBuffer.append(";lineNumber:").append(
				stackTraceElement.getLineNumber());
		stringBuffer.append(")");
		return stringBuffer.toString();
	}
	
	public static String getTraceInfo(Throwable e) {
		StringBuffer stringBuffer = new StringBuffer();
		StackTraceElement stackTraceElement = e.getStackTrace()[0];
		stringBuffer.append("(");
		stringBuffer.append("className:").append(
				stackTraceElement.getClassName());
		stringBuffer.append(";fieldName:").append(
				stackTraceElement.getFileName());
		stringBuffer.append(";methodName:").append(
				stackTraceElement.getMethodName());
		stringBuffer.append(";lineNumber:").append(
				stackTraceElement.getLineNumber());
		stringBuffer.append(")");
		return stringBuffer.toString();
	}
	
	
	//获得随机数
	public static int getRandom(int accuracy) {
		return (int) (Math.random() * accuracy);
	}
	
	public static String getUUID() {

		return UUID.randomUUID().toString();
	}
	
	public static String getRandom() {

		String str = MD5.md5(getUUID() + System.currentTimeMillis() + getRandom(999999999) + rand.nextLong()  + localhost);
		str = str.toLowerCase();
		/*String date = (new Date()).getTime()+"";
		String str =TimeUtils.getSysTimeLong().substring(2)+MD5Utils.md5(""+getRandom(999999999)).substring(8, 16)+date+(new Random()).nextInt(10);
		if(str.length()>32){
			str = str.substring(0,32);
		}*/
		return str;
	}
	
	public static String getRandom(String string) {

		String str = MD5.md5(getUUID() + System.currentTimeMillis() + getRandom(999999999) + rand.nextLong()  + localhost +  string);
		str = str.toLowerCase();

		return str;
	}
	
	public static String encodeBase64String(String data) {
    	return Base64.encode(data.getBytes()).trim();
    }
	
	public static String hiddenMobile(String mobile){
		if(ValidatorUtils.isNull(mobile)||mobile.length()<11){
			return mobile;
		}
		return mobile.substring(0,3)+"******"+mobile.substring(9,11);
	}
	
	public static String hiddenNickName(String nickName){
		if(ValidatorUtils.isNull(nickName)){
			return nickName;
		}
		if(nickName.length()<7){
			return nickName.substring(0,1)+"****";
		}
		return nickName.substring(0,1)+"****"+nickName.substring(nickName.length()-1,nickName.length());
	}
	
	public static String getRewardNo(){
		List<String> randList = new ArrayList<String>();
		randList.add("1");
		randList.add("2");
		randList.add("3");
		randList.add("4");
		randList.add("5");
		randList.add("6");
		randList.add("7");
		randList.add("8");
		randList.add("9");
		randList.add("A");
		randList.add("B");
		randList.add("C");
		randList.add("D");
		randList.add("E");
		randList.add("F");
		randList.add("G");
		randList.add("H");
		randList.add("I");
		randList.add("J");
		randList.add("K");
		randList.add("L");
		randList.add("M");
		randList.add("N");
		randList.add("P");
		randList.add("Q");
		randList.add("R");
		randList.add("S");
		randList.add("T");
		randList.add("U");
		randList.add("V");
		randList.add("W");
		randList.add("X");
		randList.add("Y");
		randList.add("Z");
		Random random = new Random();
		int rand = 0;
		String result = "QTYD";
		for(int i=0;i<10;i++){
			rand = random.nextInt(randList.size());
			result = result+randList.get(rand);
		}
		return result;
	}
	
	/**
	 * 登录密码处理
	 * @param password
	 * @return
	 */
	public static String loginPwd(String password){
		return MD5.md5("@)!%" + password + ")!!$");
	}
	/**
	 * 支付密码处理
	 * @param password
	 * @return
	 */
	public static String payPwd(String password){
		return MD5.md5("@)$!" + password + "^&*()");
	}
	
	/**
	 * 短信验证码
	 * @return
	 */
	public static String smsCode(){
		StringBuffer rand = new StringBuffer("");
		while (rand.toString().length()<6) {
			rand.append((new Random()).nextInt(10));
//			rand = rand+(new Random()).nextInt(10);
		}
		return rand.toString();
	}
	
	/**
	 * 获得新浪用户ID
	 * @param args
	 */
	public static String sinaUID(Long uid){
		String rand = "";
		while (rand.length()<5) {
			rand = rand+(new Random()).nextInt(10);
		}
		int userIdLen = 4;
		String  userId= uid+"";
		while (userId.length()<userIdLen) {
			userId = "0"+userId;
		}
		if(userId.length()>userIdLen){
			userId = userId.substring(0,userIdLen);
		}
		return "QTYD"+TimeUtils.getSysdateInt().substring(2)+rand+userId+(new Random()).nextInt(10);
	}
	
	/**
	 * 获得16位的记录编号
	 * @return
	 */
	public static String getRecordNo(){
		String date = (new Date()).getTime()+"";
		date = date.substring(date.length()-3);
		return TimeUtils.getSysTimeLong().substring(2)+date+(new Random()).nextInt(10);
	}
	
	public static String getDesKey(){
		String md5 = MD5.md5((new Date()).getTime()+"");
		return md5.substring(8,16).toUpperCase();
	}
	
	public static String encodeCardId(String cardId){
		return DesUtils.encrypt(cardId, "QTYD@)!%");//qtyd2015
	}
	
	public static String decodeCardId(String cardId){
		return DesUtils.decrypt(cardId, "QTYD@)!%");
	}
	
	public static String birthday(String cardId){
		if (cardId.length()==18){
            return cardId.substring(6,10)+"-"+cardId.substring(10,12)+"-"+cardId.substring(12,14);
       }
      return "";
	}
	
	public static String encodeBankAccount(String bankAccount){
		return DesUtils.encrypt(bankAccount, "%!*^%#QT");
	}
	
	public static String decodeBankAccount(String bankAccount){
		return DesUtils.decrypt(bankAccount, "%!*^%#QT");
	}
	
	public static String encodeUserToken(String redisKey){
		return DesUtils.encrypt(redisKey, "!@)#!&#&");
	}
	
	public static String decodeUserToken(String redisKey){
		return DesUtils.decrypt(redisKey, "!@)#!&#&");
	}
	
	public static String encodeJDBC(String value){
		return DesUtils.encrypt(value, "wNjQ3ODg");
	}
	
	public static Map<String, String> pageInfo(int currentPage,int pageSize,int totalRows){
		int totalPage = 1;
		if(totalRows>0){
			totalPage = (totalRows%pageSize==0)?(totalRows/pageSize):((totalRows/pageSize)+1);
		}
		if(currentPage>totalPage){
			currentPage = totalPage;
		}		
		Map<String, String> result = new LinkedHashMap<String, String>();
		result.put("total_count", totalRows+"");
		result.put("total_page", totalPage+"");
		result.put("page_size", pageSize+"");
		result.put("current_page", currentPage+"");
		return result;
	}
	
	public static List<Map<String, Object>> convertDataMapToApiMap(List<Map<String, Object>> dbResult,String key){
		if(dbResult==null){
			return null;
		}
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Map<String, Object> obj = null;
		for (Map<String, Object> map : dbResult) {
			obj = new HashMap<String, Object>();
			obj.put(key, map);
			result.add(obj);
		}
		return result;
	}
	
	public static int howOld(String cardId){
		if(cardId==null||"".equals(cardId)){
			return 0;
		}
		int len = cardId.length();
		if(len==18){
			cardId = cardId.substring(6,14);
		}else if(len==15){
			cardId = "19"+cardId.substring(6,12);
		}
		String oldYear = cardId.substring(0,4);
		String md = cardId.substring(4,8);
		String now = TimeUtils.getSysdateInt();
		String nowYear = now.substring(0,4);
		String nmd = now.substring(4,8);
		int age = Integer.parseInt(nowYear)-Integer.parseInt(oldYear);
		if(md.compareTo(nmd)>0){
			age = age-1;
		}
		return age;
	}
	
	public static String round(String value,int digits){
		return round(Double.valueOf(value),digits);
	}
	
	public static String round(double value,int digits){
		return String.format("%."+digits+"f", value);
		/*BigDecimal bg = new BigDecimal(value);
        double result =  bg.setScale(digits, BigDecimal.ROUND_HALF_UP).doubleValue();
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
        nf.setGroupingUsed(false);
        nf.setMinimumFractionDigits(digits);
        return nf.format(result);*/
	}
	
	public static String roundDown(double value,int digits){
		BigDecimal bg = new BigDecimal(value);
        double result =  bg.setScale(digits, BigDecimal.ROUND_DOWN).doubleValue();
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
        nf.setGroupingUsed(false);
        nf.setMinimumFractionDigits(digits);
        return nf.format(result);
	}
	
	public static double add(double v1,double v2){
		String tp = round(v1, 2);
		String tp2 = round(v2, 2);
		double result = Double.valueOf(tp)+Double.valueOf(tp2);
		return Double.valueOf(CommonUtils.round(result, 2));
	}
	
	public static double add(String v1,String v2){
		double result = Double.valueOf(v1)+Double.valueOf(v2);
		return Double.valueOf(CommonUtils.round(result, 2));
	}
	
	public static double sub(double v1,double v2){
		String tp = round(v1, 2);
		String tp2 = round(v2, 2);
		double result = Double.valueOf(tp)-Double.valueOf(tp2);
		return Double.valueOf(CommonUtils.round(result, 2));
	}
	
	public static double sub(String v1,String v2){
		double result = Double.valueOf(v1) - Double.valueOf(v2);
		return Double.valueOf(CommonUtils.round(result, 2));
	}
	
	public static int getWordLength(String str) {
		str = StringUtils.nullToStrTrim(str);
		return str.replaceAll("[^\\x00-\\xff]", "**").length();
	}
	

	public static int getRealLength(String str) {

		return getRealLength(str, CHARSETNAME_DEFAULT);
	}
	
	/**
	 * 获得标的期数
	 *  
	 * @param borrowName 标全名
	 * @param bracketsFlg  新的标题是否需要携带左右括弧
	 */
	public static String getBorrowTitle(String borrowName,boolean bracketsFlg){
		if(borrowName.indexOf("【")>0){
			int end = borrowName.length();
			if(borrowName.indexOf("】")>0){
				end = borrowName.indexOf("】");
			}
			borrowName = borrowName.substring(borrowName.indexOf("【")+1,end);
		}
		if(bracketsFlg){
			borrowName = "【"+borrowName+"】";
		}
		return borrowName;
	}

	public static int getRealLength(String str, String charsetName) {

		str = StringUtils.nullToStrTrim(str);

		if (ValidatorUtils.isNull(str)) {
			return 0;
		}

		try {
			return str.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e) {
			log.error(getTraceInfo() +" str:"+str+"  charsetName:"+charsetName+"\t"+ StringUtils.nullToStrTrim(e.getMessage()));
			return 0;
		}
	}
	
	public static void main(String[] args) {
		/*String accessid = getRandom("123121321");
		System.out.println(accessid);
		String accesskey = encodeBase64String(getRandom(accessid));
		System.out.println(accesskey);*/
//		System.out.println(smsCode());
		
//		System.out.println(hiddenNickName("落月破"));
//		System.out.println(hiddenNickName("落月破晓"));
//		System.out.println(hiddenNickName("落月破晓的"));
//		System.out.println(hiddenNickName("落月破晓的日"));
//		System.out.println(hiddenNickName("落月破晓的日子"));
//		System.out.println(hiddenNickName("落月破晓的日子日"));
		System.out.println(round(1925.25,2));
	}

	

}

