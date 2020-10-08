package com.gameofthree.gamelogic;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.xml.validation.Validator;

import com.gameofthree.GameServer;
import com.gameofthree.controller.GameModeController;
import com.gameofthree.model.HumanGame;
import com.gameofthree.model.Player;
import com.gameofthree.threads.PlayerInputManagement;
import com.gameofthree.threads.PlayerSetUp;
import com.gameofthree.validator.isDivisibleOfThree;

public class PlayerInput {
	
	private Socket socket; 
	private GameServer server;
	private PrintWriter writer;
	Console console = System.console();
	isDivisibleOfThree validate=new isDivisibleOfThree();

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
			//clean all
			//reader.read();
			 
			OutputStream output = socket.getOutputStream();
			System.out.println("Status - " +status);
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
			if(!(playerAction.equals("-1") || playerAction.equals("1") || playerAction.equals("0"))){
				isValid=false;
				server.broadcastTo("Invalid input, must be in -1,0 or 1", thread);
			}else if(player.getName()!=server.getGame().getState()){
				isValid=false;
				server.broadcastTo("Not your turn, you must wait", thread);
			}
			else if((server.getGame().getNumerPlayed()+Integer.parseInt(playerAction))%3!=0){
				isValid=false;
				server.broadcastTo("Invalid input, not divisible by 3", thread);
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
			server.broadcastTo("Invalid input, must be in -1,0 or 1", thread);
		}
		else if(!validate.validate(currentNumber+Integer.parseInt(playerAction))){
			isValid=false;
			server.broadcastTo("Invalid input, not divisible by 3", thread);
		}else {
			isValid=true;
		}
		return isValid;
	}

	
	
}
