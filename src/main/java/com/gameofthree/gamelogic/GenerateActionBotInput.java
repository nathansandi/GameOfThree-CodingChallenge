package com.gameofthree.gamelogic;


import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import com.gameofthree.utils.PropertiesConfig;
import com.gameofthree.validator.isDivisibleOfThree;

public class GenerateActionBotInput{

	
    public static int getNewInput(int currentNumber) {    	        
        return setNewInput(currentNumber);
    }
    
	private static int setNewInput(int currentNumber) {
		isDivisibleOfThree inputCheck = new isDivisibleOfThree(); 
		int numberToReturn=0;
		//test -1
		if(inputCheck.validate(currentNumber-1)) { numberToReturn=  -1; }
		//test 0
		if(inputCheck.validate(currentNumber)) { numberToReturn=  0; }
		//test 1 
		if(inputCheck.validate(currentNumber+1)) { numberToReturn=  1; }
		//Or
		
		return numberToReturn;
	}
	

    
}
