package com.test.util;

public final class StringCalculatorConstants {

	private StringCalculatorConstants() {}
	
	public static int 		RIGHT_PARENTHESIS 			= 40;
	public static int 		LEFT_PARENTHESIS 			= 41;
	public static int 		MULTIPLICATION 				= 42;
	public static int 		ADDITION 					= 43;
	public static int 		POWER_OF 					= 94;
	public static int 		DIVISION 					= 47;
	public static int 		SUBSTRACTION 				= 45;

	public static String 	INVALID_EXPR 				= "Invalid Expression";
	
	public static void main(String[] args) {
		
		System.out.println((char) LEFT_PARENTHESIS);
		
	}
}
