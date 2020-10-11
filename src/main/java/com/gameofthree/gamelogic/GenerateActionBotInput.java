package com.gameofthree.gamelogic;

public class GenerateActionBotInput{
	
	
    /**
     * 
     * This class is responsable to manage and test the bot input and manage for input in order to avoid errors according to the restrictions
     * in the game
     * 
     */
	
    public static int getNewInput(int currentNumber) {    	        
        return setNewInput(currentNumber);
    }
    
	private static int setNewInput(int currentNumber) {
		int numberToReturn=0;
		//test -1
		if(isDivByThree(currentNumber-1)) { numberToReturn=  -1; }
		//test 0
		if(isDivByThree(currentNumber)) { numberToReturn=  0; }
		//test 1 
		if(isDivByThree(currentNumber+1)) { numberToReturn=  1; }
		
		return numberToReturn;
	}
	private static boolean isDivByThree(int in)
	{
		
		return(in%3==0);
	}
	

    
}
