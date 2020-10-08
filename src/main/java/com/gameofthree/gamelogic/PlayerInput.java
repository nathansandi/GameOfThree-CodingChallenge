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
				//Validate input 1,0,-1
				if(!(playerAction.equals("-1") || playerAction.equals("1") || playerAction.equals("0"))){
					isValid=false;
					server.broadcastTo("Invalid input, must be in -1,0 or 1", thread);
				}else if(player.getName()!=server.getGame().getState()){
					isValid=false;
					server.broadcastTo("Not your turn, you must wait", thread);
				}
				else if(!validate.validate(currentNumber+Integer.parseInt(playerAction))){
					isValid=false;
					server.broadcastTo("Invalid input, not divisible by 3", thread);
				}else {
					isValid=true;
				}
				//Valida if is divisible peer 3
				
			}while(!isValid);
				
			}catch (IOException ex) {
	                System.out.println("Error in PlayerThread: " + ex.getMessage());
	                ex.printStackTrace();
			}
		
		return playerAction;
	
	 }

	
	  public void sendMessage(String message) {
	        writer.println(message);
	  }
	  

	
	
}
