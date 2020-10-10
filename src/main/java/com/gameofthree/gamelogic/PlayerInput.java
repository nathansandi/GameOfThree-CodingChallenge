package com.gameofthree.gamelogic;

import java.io.*;
import java.net.Socket;


import com.gameofthree.GameServer;
import com.gameofthree.exceptions.GameExceptionHandler;
import com.gameofthree.exceptions.GameGeneralException;
import com.gameofthree.exceptions.GameTurnException;
import com.gameofthree.exceptions.GameValidationException;
import com.gameofthree.model.Player;
import com.gameofthree.threads.PlayerSetUp;


public class PlayerInput {
	
	private Socket socket; 
	private GameServer server;
	private PrintWriter writer;
	Console console = System.console();

	public PlayerInput(Socket socket, GameServer server) {
		this.socket=socket;
		this.server=server;
	}
	
	@Override
	public String toString() {
		return "PlayerThread [socket=" + socket + ", server=" + server + ", writer=" + writer + "]";
	}

	public String run(Player player, PlayerSetUp thread, int currentNumber, String status) {
		String playerAction="";
		boolean isValid =true;
		try {			
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));				
			do {
				
				playerAction = reader.readLine();
				if(server.getGame()!=null) {
					isValid=validateMultiplePlayerGame( playerAction,  thread,  player);
				}else {
					isValid= validateSinglePlayerGame( playerAction,  thread,  player,  currentNumber);
				}

			}while(!isValid);
				
			}catch (IOException ex) {
	                System.out.println("Error in PlayerThread: " + ex.getMessage());
	                ex.printStackTrace();
			}
		
		return playerAction;
	
	 }
	
	public boolean validateMultiplePlayerGame(String playerAction, PlayerSetUp thread, Player player) {
		boolean isValid=false;
		
		if(!server.getGame().isWin()) {
			if(player.getName()!=server.getGame().getState()){
				isValid=false;
				new GameExceptionHandler(socket, server).handleException(new GameTurnException("Not your turn, wait...!"), thread);
			}
			else if(!(playerAction.equals("-1") || playerAction.equals("1") || playerAction.equals("0"))){
				isValid=false;
				new GameExceptionHandler(socket, server).handleException(new GameGeneralException("Invalid input, must be in -1,0 or 1"), thread);
			}
			else if(!isDivisibleBy.isDivisibleBy3(server.getGame().getNumerPlayed()+Integer.parseInt(playerAction))){
				isValid=false;		
				new GameExceptionHandler(socket, server).handleException(new GameValidationException("Result is not divisible by 3"), thread);
			}else {
				isValid=true;
			}
			return isValid;
		}else {
			server.broadcastTo("Game finished - You win!", thread);
			isValid=true;
		}
		return isValid;
	}
	
	public boolean validateSinglePlayerGame(String playerAction, PlayerSetUp thread, Player player, int currentNumber) {
		boolean isValid=false;
		if(!(playerAction.equals("-1") || playerAction.equals("1") || playerAction.equals("0"))){
			isValid=false;
			//server.broadcastTo("Invalid input, must be in -1,0 or 1", thread);
			new GameExceptionHandler(socket, server).handleException(new GameGeneralException("Invalid input, must be in -1,0 or 1"), thread);
		}
		else if(!isDivisibleBy.isDivisibleBy3(currentNumber+Integer.parseInt(playerAction))){
			isValid=false;
			new GameExceptionHandler(socket, server).handleException(new GameValidationException("Result is not divisible by 3"), thread);
		}else {
			isValid=true;
		}
		return isValid;
	}

	
	
}
