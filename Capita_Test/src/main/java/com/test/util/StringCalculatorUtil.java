package com.test.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StringCalculatorUtil {

	private final List<Integer> operatorsList = Arrays.asList(StringCalculatorConstants.ADDITION, StringCalculatorConstants.SUBSTRACTION, StringCalculatorConstants.MULTIPLICATION,
			StringCalculatorConstants.DIVISION, StringCalculatorConstants.POWER_OF);
	
	public boolean validateExpression(String expr) {
		try {
			return validateParentheses(expr) && validateOperators(expr);
		}catch (Exception e) {
			return false;
		}
		
	}

	private boolean validateParentheses(String expr) {
		int l_cnt = 0;
		int r_cnt = 0;
		
		int i = 0;
		
		for(char c: expr.toCharArray()) {
			
			if((int)c == StringCalculatorConstants.LEFT_PARENTHESIS) {
				l_cnt ++;
				
				if((i > 0 && i < expr.length() - 1) && Character.isDigit(expr.charAt(i + 1))) { //condition to check )78
					return false;
				}
			} else if ((int)c == StringCalculatorConstants.RIGHT_PARENTHESIS) {
				r_cnt++;
				
				if((i > 0 && i < expr.length()) && Character.isDigit(expr.charAt(i - 1))) { // condition to check 89(
					return false;
				}
			}
			
			if(l_cnt > 0 && r_cnt == 0) {
				return false;
			}
			i++;
		}
		
		if(l_cnt != r_cnt) {
			return false;
		}
		
		return true;
	}
	
	private boolean validateOperators(String expr) {
		
		int i = 0;
		char exprArr[] = expr.toCharArray();
		
		for(char c: exprArr) {
			
			if(i == 0 && operatorsList.contains((int)c)) {
				return false;
			}
			
			if(operatorsList.contains((int)c))  {
				
				if((i + 1) == exprArr.length){
					return false;
				}
				
				if(!((Character.isDigit(exprArr[i - 1]) && Character.isDigit(exprArr[i + 1])) || // condition to check 1+1
						(((int)exprArr[i - 1] == StringCalculatorConstants.LEFT_PARENTHESIS) && ((int)exprArr[i + 1] == StringCalculatorConstants.RIGHT_PARENTHESIS)) || // condition to check )+(
						((int)exprArr[i - 1] == StringCalculatorConstants.LEFT_PARENTHESIS && Character.isDigit(exprArr[i + 1])) || // condition to check )+1
						(Character.isDigit(exprArr[i - 1]) && (int)exprArr[i + 1] == StringCalculatorConstants.RIGHT_PARENTHESIS))) { // condition to check 1+(
					return false;
				}

			}
			i++;
		}
		return true;
		
	}
	
	public String evaluateExpression(String expr) {
		
		int cnt = 0;
		int cnt1 = 0;
		
		try {
			for(int i = 0; i < expr.length(); i++) {

				if((int)expr.charAt(i) == StringCalculatorConstants.RIGHT_PARENTHESIS && cnt1 == 0) {
					cnt = i;
				}
				if((int)expr.charAt(i) == StringCalculatorConstants.LEFT_PARENTHESIS) {
					cnt1 = i;
					break;
				}
				
			}
			expr = expr.substring(0, cnt) + evaluateExpr(expr.substring(cnt+1, cnt1)) + expr.substring(cnt1 + 1);
			
			if(expr.contains(")") || expr.contains("(")) {
				expr = evaluateExpression(expr);
			} else {
				expr = evaluateExpr(expr);
			}
		}catch (Exception e) {
			return StringCalculatorConstants.INVALID_EXPR;
		}
		
		return expr;
	}
	
	private String evaluateExpr(String expr) {
		
		String right = null;
		String left = null;
		
		for(int i = 0;i < expr.length();i++) {
			
			if(expr.charAt(i) == '^' && i > 0) {
				
				right = getRightNumber(expr.substring(0, i), i);
				left = getLeftNumber(expr.substring(i+1), i);
				
				Double r =  Math.pow(Integer.parseInt(right), Integer.parseInt(left));
				expr = expr.substring(0, i - right.length()) + r.intValue() + expr.substring(i + left.length() + 1);
				i = 0;
			}
		}
		
		for(int i = 0;i < expr.length();i++) {
			
			if(expr.charAt(i) == '/' && i > 0) {
				
				right = getRightNumber(expr.substring(0, i), i);
				left = getLeftNumber(expr.substring(i + 1), i);
				
				double r =  Double.parseDouble(right) / Double.parseDouble(left);
				expr = expr.substring(0, i - right.length()) + r + expr.substring(i + left.length() + 1);
				i = 0;
			}
		}
			
		for(int i = 0;i < expr.length();i++) {
			
			if(expr.charAt(i) == '*' && i > 0) {
				
				right = getRightNumber(expr.substring(0, i), i);
				left = getLeftNumber(expr.substring(i+1), i);
				
				double r =  Double.parseDouble(right) * Double.parseDouble(left);
				expr = expr.substring(0, i - right.length()) + r + expr.substring(i + left.length() + 1);
				i = 0;
			}
		}
		
		for(int i = 0;i < expr.length();i++) {
			
			if(expr.charAt(i) == '+' && i > 0) {
				
				right = getRightNumber(expr.substring(0, i), i);
				left = getLeftNumber(expr.substring(i+1), i);
				
				double r =  Double.parseDouble(right) + Double.parseDouble(left);
				expr = expr.substring(0, i - right.length()) + r + expr.substring(i + left.length() + 1);
				i = 0;
			}
		}
		
		for(int i = 0;i < expr.length();i++) {
			
			if(expr.charAt(i) == '-' && i > 0) {
				
				right = getRightNumber(expr.substring(0, i), i);
				left = getLeftNumber(expr.substring(i+1), i);
				
				double r =  Double.parseDouble(right) - Double.parseDouble(left);
				expr = expr.substring(0, i - right.length()) + r + expr.substring(i + left.length() + 1);
				i = 0;
			}
		}
		
		return expr;
	}
	
	private String getRightNumber(String expr, int index) {
		
		for(int i = expr.length() - 1;i >= 0;i--) {
			
			if(operatorsList.contains((int)expr.charAt(i)))  {
				
				if(expr.charAt(i) == '-') {
					return expr.substring(i);
				}
				return expr.substring(i+1);
			}
		}
		
		return expr;
	}
	
	private String getLeftNumber(String expr, int index) {
		
		for(int i = 0;i < expr.length();i++) {
			
			if(operatorsList.contains((int)expr.charAt(i)))  {
				return expr.substring(0,i);
			}
		}
		return expr;
	}
	

	public static void main(String[] args) {
		
		System.out.println(new StringCalculatorUtil().evaluateExpression("(8*5/8)-(3/1)-5*7+((1+2)*(7*3))"));
	}
}
