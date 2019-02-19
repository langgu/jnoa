package com.thinkgem.jeesite.test;

public class Test {

	public static void main(String[] args) {
		//测试文件
		System.out.println(Test.conver("礼煜明德办公App后台管理系统"));
	}

	public static String conver(String str) {
		String result = "";
		for(int i = 0; i < str.length(); i++) {
			String temp = "";
			int strInt = str.charAt(i);
			if(strInt > 127) {
				temp += "\\u" + Integer.toHexString(strInt);
			} else {
				temp = String.valueOf(str.charAt(i));
			}

			result += temp;
		}
		return result;
	}
}
