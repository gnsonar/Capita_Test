package com.test.impl;

import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.test.StringCalculator;

@Component
public class StringCalculatorImpl implements StringCalculator {

	public String solveMathematicalExpression(String exp) throws Exception {

		String[] input = exp.split("\\n");
		
		if(Integer.parseInt(input[0]) != (input.length - 1)) {
			return "INVALID INPUT";
		}
		
		int noOfExp = Integer.parseInt(input[0]);
		
		IntStream.rangeClosed(1, noOfExp).forEach(i -> {
			System.out.println(input[i]);
		});
		return null;
	}
    
}
