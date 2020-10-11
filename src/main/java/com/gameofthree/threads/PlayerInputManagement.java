package com.gameofthree.threads;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.gameofthree.GameClient;
import com.gameofthree.controller.GameModeController;
import com.gameofthree.model.Player;

public class PlayerInputManagement extends Thread{
	 	private PrintWriter writer;
	    private Socket socket;
	    private GameClient client;
	    Console console = System.console();
	    
	    public PlayerInputManagement(Socket socket, GameClient client)  {
	        this.socket = socket;
	        this.client = client;
	
	 
	    try {
	            OutputStream output = socket.getOutputStream();
	            writer = new PrintWriter(output, true);
	        } catch (IOException ex) {
	            System.out.println("Error getting output stream: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
		/**
		 * Thread - playerInputManagement
		 * Thread to manage player input in terminal on game client
		 */
	    public void run() {
	    	String gameMode ="";
	    	String readConsoleGeneral="";
	    	String input="";
	        
	        String playerName = console.readLine("Enter the player name:");
	        writer.println(playerName);
	        
	        
	        gameMode = console.readLine("\n Now, chose one of options to start: "
	        		+ "\n [1] - BOT vs BOT"
	        		+ "\n [2] - Player vs BOT"
	        		+ "\n [3] - 2 Players (2 players must be connected)"
	        		+ "\n Or Type Quit to exit.");
	        writer.println(gameMode);
	        
	        do {
	             readConsoleGeneral = console.readLine();
	        	 writer.println(readConsoleGeneral);
	 
	        } while (!readConsoleGeneral.equals("Quit"));
		   
	        
	    }
	    
	   
	    
}
