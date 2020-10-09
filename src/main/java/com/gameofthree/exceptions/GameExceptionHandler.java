package com.gameofthree.exceptions;

import java.net.Socket;


import com.gameofthree.GameServer;
import com.gameofthree.model.Player;
import com.gameofthree.threads.PlayerSetUp;

public class GameExceptionHandler implements ExceptionHandler<GameGeneralException> {

		 	private Socket socket;
		    private GameServer server; 
	
		    public GameExceptionHandler(Socket socket, GameServer server) {
		        this.socket = socket;
		        this.server = server;
		    }
		    /**
		     * Handle GameException by sending error message to the player sockets 
		     */
		    @Override
		    public void handleException(GameGeneralException ex, PlayerSetUp currentPlayer) {
		        String error = getError(ex);
		        server.broadcastTo(error, currentPlayer);
		    }

		    
		    private String getError(GameGeneralException ex) {
		        return "ERROR: " +ex.getMessage();
		    }
}
