package com.gameofthree;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.gameofthree.threads.ReadThread;
import com.gameofthree.utils.PropertiesConfig;
import com.gameofthree.threads.PlayerInputManagement;

public class GameClient {
	static {initConfigLoader();}
	private static final String PORT = PropertiesConfig.getProperties().getProperty("com.gameofthree.server.port");
	private static final String HOST = PropertiesConfig.getProperties().getProperty("com.gameofthree.server.ip");
	private String hostName;
	
	private static 
	int port=Integer.parseInt(PORT);
	
	public static void main(String[] args) {
		String hostname = HOST;
	    GameClient client = new GameClient(hostname, port);
	    client.execute();
	}
	
	public GameClient(String hostName, int port) {
		this.hostName = hostName;
		this.port = port;

	}
	
    /**
     * Client class - Start game.
     * Start client threads. Restrictions: If there are more than 2 players, server will close the socket for the 3rd player.
     * 
     */
	public void execute() {
		try{				
				Socket socket = new Socket(hostName, port);				
				if(!socket.isClosed()) {
					System.out.println("*** Connected to the game server ***");	
						
					//Open the read Thread to the Player see messages from the server
					new ReadThread(socket, this).start();			
						
					String welcomeText = "Welcome to the Game of Three";	
					System.out.println(welcomeText);
								
					//Open the Write Thread to the player write messages to the server
					new PlayerInputManagement(socket, this).start();
				}	
		}catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }

	}
	
	private static void initConfigLoader() {
		//Initialize properties 
    	PropertiesConfig.initialize("application.properties");
	}

}
