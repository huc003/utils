package com.huc.utils;

import java.math.BigDecimal;

/**
 * 金额处理类
 * @author huc
 */
public class MoneyUtils {
	
	/**
	 * 保留小数： 向上取整
	 * @param money 金额
	 * @param digits 几位小数
	 * @return
	 */
	public static BigDecimal roundUp(String money,int digits){
		BigDecimal bd = new BigDecimal(money);
		return bd.setScale(digits, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 保留两数：向下取整
	 * @param money 金额
	 * @param digits 几位小数
	 * @return
	 */
	public static BigDecimal roundDown(String money,int digits){
		BigDecimal bd = new BigDecimal(money);
		return bd.setScale(digits, BigDecimal.ROUND_HALF_DOWN);
	}
	
	/**
	 * 金额加法计算 money1+money2
	 * @param money1
	 * @param money2
	 * @return
	 */
	public static BigDecimal add(String money1,String money2){
		BigDecimal bd1 = new BigDecimal(money1);
		BigDecimal bd2 = new BigDecimal(money2);
		return bd1.add(bd2);
	}
	
	/**
	 * 金额减法计算 money1-money2
	 * @param money1
	 * @param money2
	 * @return
	 */
	public static BigDecimal subtract(String money1,String money2){
		BigDecimal bd1 = new BigDecimal(money1);
		BigDecimal bd2 = new BigDecimal(money2);
		return bd1.subtract(bd2);
	}
	
	/**
	 * 金额乘法计算 money1*money2
	 * @param money1
	 * @param money2
	 * @return
	 */
	public static BigDecimal multiply(String money1,String money2){
		BigDecimal bd1 = new BigDecimal(money1);
		BigDecimal bd2 = new BigDecimal(money2);
		return bd1.multiply(bd2);
	}
	
	/**
	 * 金额除法计算 money1/money2
	 * @param money1 
	 * @param money2
	 * @return
	 */
	public static BigDecimal divide(String money1,String money2){
		BigDecimal bd1 = new BigDecimal(money1);
		BigDecimal bd2 = new BigDecimal(money2);
		return bd1.divide(bd2);
	}
	
	public static void main(String[] args) {
//		System.out.println(roundUp("12.655",2));
//		System.out.println(roundDown("12.655",2));
//		System.out.println(add("12.354","45.3"));
//		System.out.println(subtract("120.3","45.3"));
		System.out.println(multiply("12","4"));
		
	}
}	
