package com.gameofthree;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.gameofthree.model.Player;
import com.gameofthree.threads.ReadThread;
import com.gameofthree.threads.PlayerInputManagement;

public class GameClient {
	private String hostName;
	private Player player = new Player("");
	private static 
	int port=9998;
	public static void main(String[] args) {

	        String hostname = "localhost";
	 
	        GameClient client = new GameClient(hostname, port);
	        client.execute();
	}
	
	public GameClient(String hostName, int port) {
		this.hostName = hostName;
		this.port = port;

	}
	
	/* -- Method to try create the connection with the server, in case of fail 
 		  the exceptions will be showed in the terminal        -- */
	
	public void execute() {
		try{				
				Socket socket = new Socket(hostName, port);
				System.out.println("*** Connected to the game server ***");
				
				//Open the read Thread to the Player see messages from the server
				new ReadThread(socket, this, player).start();
		       
				String welcomeText = "Welcome to the Game of Three";	
				System.out.println(welcomeText);
				
				//Open the Write Thread to the player write messages to the server
				new PlayerInputManagement(socket, this).start();
							
				
		}catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
		
		
	}
	
	
	
}
