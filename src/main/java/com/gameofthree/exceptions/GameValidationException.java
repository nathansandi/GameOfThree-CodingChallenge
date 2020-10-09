package com.gameofthree.exceptions;

public class GameValidationException extends GameGeneralException{
    /**
    * Exception when the play is not valid
    */
	  public GameValidationException(String message) {
		   super(message);
	  }

}
