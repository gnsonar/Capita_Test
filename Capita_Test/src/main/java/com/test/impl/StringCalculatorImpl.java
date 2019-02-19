package com.test.impl;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.StringCalculator;
import com.test.util.StringCalculatorConstants;
import com.test.util.StringCalculatorUtil;

@Service
public class StringCalculatorImpl implements StringCalculator{

	@Autowired
	private StringCalculatorUtil stringCalculatorUtil;
	
	public String solveMathematicalExpression(String exp) {

		String[] input = exp.split("\\n");
		StringBuffer output = new StringBuffer();
		
		try {
			
			if(Integer.parseInt(input[0]) != (input.length - 1)) {
				return "INVALID INPUT";
			}
			
			int noOfExp = Integer.parseInt(input[0]);
			
			IntStream.rangeClosed(1, noOfExp).forEach(i -> {
				output.append("Case #" + i + ":");
				output.append(stringCalculatorUtil.validateExpression(input[i]) ? new Double(stringCalculatorUtil.evaluateExpression(input[i])).intValue() : StringCalculatorConstants.INVALID_EXPR);
				output.append("\n");
			});
			return output.toString();
			
		} catch (Exception e) {
			e.printStackTrace();;
			return "ERROR IN CALCULATION";
		}
		
	}
}
