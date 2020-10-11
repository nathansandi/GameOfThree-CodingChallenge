package com.gameofthree.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import com.gameofthree.GameServer;
import com.gameofthree.controller.GameModeController;
import com.gameofthree.model.Player;

public class PlayerSetUp extends Thread{
	
	private Socket socket; 
	private GameServer server;
	private PrintWriter writer;


	public PlayerSetUp(Socket socket, GameServer server) {
		this.socket=socket;
		this.server=server;

	}
	
	@Override
	public String toString() {
		return "PlayerThread [socket=" + socket + ", server=" + server + ", writer=" + writer + "]";
	}
	/**
	 * Thread - player Set Up
	 * Thread to manage player set up inputs.
	 */
	public void run() {
		try {
			String in="";
			
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);
			
		
			//Read the player Name 
			String playerName= reader.readLine();
			Player currentPlayer = new Player(playerName);
			
			
			//Read the game mode 			
			while(true){
				in= reader.readLine();	
			    GameModeController gameStart = new GameModeController(socket, server);
			    gameStart.gameModeStarter(in, this, currentPlayer); 
			    if(in=="1" || in=="2" || in=="3" || in=="Quit") {
			    	break;
			    }
			}
			
			}catch (IOException ex) {
	                System.out.println("Error in PlayerThread: " + ex.getMessage());
	        }
	
		}

	  public void sendMessage(String message) {
	        writer.println(message);
	  }

}
		
	