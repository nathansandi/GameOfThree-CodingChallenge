package com.gameofthree.gamelogic;

public class WinnerOutput {


	public WinnerOutput() {

	}
	
	/**
	 * Method isWin
	 * Return true if the received number is 1
	 * 
	 * @param received number in turn
	 * @return true if is 1, false if is not
	 */

	public boolean isWin(int number) {
		if(number==1) {
			return true;
		}
		return false;
		
	}
}
