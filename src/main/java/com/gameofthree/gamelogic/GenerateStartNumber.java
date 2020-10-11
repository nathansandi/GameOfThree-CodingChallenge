package com.gameofthree.gamelogic;

import java.util.Random;
import com.gameofthree.utils.PropertiesConfig;

public class GenerateStartNumber {


   
	private static final String MIN_POSSIBLE_INPUT_NUMBER = PropertiesConfig.getProperties().getProperty("com.gameofthree.game.min_number");
	private static final String MAX_POSSIBLE_INPUT_NUMBER = PropertiesConfig.getProperties().getProperty("com.gameofthree.game.max_numner");
    
    /**
     * Method getStartNumber
     * Return the number between the min and max value
     * 
     * @return input for the game 
     */

    public static int getStartNumber() {
        int minInputNumber = Integer.parseInt(MIN_POSSIBLE_INPUT_NUMBER);
        int maxInputNumber = Integer.parseInt(MAX_POSSIBLE_INPUT_NUMBER);
  
        int input = randomBetween(minInputNumber, maxInputNumber);
        return input;
    }

	private static int randomBetween(int minInputNumber, int maxInputNumber) {
		// TODO Auto-generated method stub
        return new Random().nextInt(maxInputNumber-minInputNumber) + minInputNumber;
	
	}

    
}
