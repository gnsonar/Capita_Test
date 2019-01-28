package com.test;

import org.springframework.stereotype.Component;

import com.test.impl.StringCalculator;

@Component
public class StringCalculatorImpl implements StringCalculator {

	public String solveMathematicalExpression(String exp) throws Exception {

		String[] input = exp.split("\\n");
		
		if(Integer.valueOf(input[0]) != (input.length - 1)) {
			return "INVALID INPUT";
		}
		return null;
	}
    
}
