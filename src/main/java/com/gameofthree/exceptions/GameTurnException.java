package com.gameofthree.exceptions;

public class GameTurnException extends GameGeneralException {
      /**
      * Exception when player plays out of his turn
      */
	  public GameTurnException(String message) {
		   super(message);
	  }

}
