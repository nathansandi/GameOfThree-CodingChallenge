package com.gameofthree.gamelogic;




public class GenerateActionBotInput{

	
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
		//Or
		
		return numberToReturn;
	}
	private static boolean isDivByThree(int in)
	{
		
		return(in%3==0);
	}
	

    
}
