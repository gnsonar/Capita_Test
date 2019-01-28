package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.StringCalculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-config.xml" })
public class StringCalculatorTest 
{

	@Autowired
	private StringCalculator stringCalculator;
	
	
	@Test
    public void shouldAnswerWithTrue()
    {
		StringBuffer input = new StringBuffer().append("4").
				append("\n").
				append("7+(6*5^2+3-4/2)").
				append("\n").
				append("7+(67(56*2))").
				append("\n").
				append("8*+7").
				append("\n").
				append("(8*5/8)-(3/1)-5");
        try {
			stringCalculator.solveMathematicalExpression(input.toString());
		} catch (Exception e) {
			
		}
    }
}
