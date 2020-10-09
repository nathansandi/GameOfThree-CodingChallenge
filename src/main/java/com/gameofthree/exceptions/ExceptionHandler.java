package com.gameofthree.exceptions;

import com.gameofthree.model.Player;
import com.gameofthree.threads.PlayerSetUp;

public interface ExceptionHandler<E extends RuntimeException> {
	
    void handleException(E ex, PlayerSetUp player);

	
    
}
