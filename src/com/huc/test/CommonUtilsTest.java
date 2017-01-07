package com.huc.test;

import com.huc.utils.CommonUtils;

public class CommonUtilsTest {
	public static void main(String[] args) {
		round();
	}
	
	public static void round(){
		System.out.println(CommonUtils.round(12.36, 2));
	}
}
