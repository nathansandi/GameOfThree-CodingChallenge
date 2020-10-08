package com.gameofthree.gamelogic;


import java.util.Optional;
import java.util.Random;

import com.gameofthree.utils.PropertiesConfig;

public class GenerateStartNumber {


   
	private static final String MIN_POSSIBLE_INPUT_NUMBER = PropertiesConfig.getProperties().getProperty("com.gameofthree.game.min_number");
	private static final String MAX_POSSIBLE_INPUT_NUMBER = PropertiesConfig.getProperties().getProperty("com.gameofthree.game.max_numner");
    
    public static int getStartNumber() {
        System.out.println("Max_-"+MIN_POSSIBLE_INPUT_NUMBER+" Min - "+MAX_POSSIBLE_INPUT_NUMBER);
        
        int minInputNumber = Integer.parseInt(MIN_POSSIBLE_INPUT_NUMBER);
        int maxInputNumber = Integer.parseInt(MAX_POSSIBLE_INPUT_NUMBER);
  
        int input = randomBetween(minInputNumber, maxInputNumber);
        System.out.println("Input - "+ input);
        return input;
    }

	private static int randomBetween(int minInputNumber, int maxInputNumber) {
		// TODO Auto-generated method stub
        return new Random().nextInt(maxInputNumber-minInputNumber) + minInputNumber;
	
	}

    
}
