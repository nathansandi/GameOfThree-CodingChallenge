package com.gameofthree.commands;

import java.io.IOException;
import java.net.Socket;

import com.gameofthree.GameServer;
import com.gameofthree.model.Player;
import com.gameofthree.threads.PlayerSetUp;
import com.gameofthree.threads.ReadThread;

public class Exit {
		
	//Exit the application and remove player from the thread
	private GameServer server;
	private PlayerSetUp thread;
	private Socket socket;

	
	public Exit(GameServer server, Socket socket) {
		this.socket = socket;
		this.server = server;
	}
	
	public void quitGame(PlayerSetUp thread, Player player) {
        try {
        	//Send a Message to the server in order to stop listen the Read Thread channel
        	server.broadcastTo("Game finised, type Quit to exit", thread);
        	server.broadcastTo("Thread killed", thread);
        	
        	//Call Remove player from Game Server 
			server.removePlayer(player, thread);
			//Close the Socket
	        socket.close();  
	   
        }catch (IOException ex) {
            System.out.println("Error in end the thread: " + ex.getMessage());
            ex.printStackTrace();
        }
        
	}
	
	
}
