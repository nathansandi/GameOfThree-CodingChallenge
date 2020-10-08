package com.gameofthree.exceptions;

public interface ExceptionHandler<E extends RuntimeException> {
	
    void handleException(E ex);

	
    
}
