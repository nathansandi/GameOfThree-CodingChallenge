package com.gameofthree.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Objects;
import java.util.stream.Stream;

import com.gameofthree.GameClient;
import com.gameofthree.model.Player;

//* This thread is gonna be used when 2 players are interacting */

//* Thread responsable to read server input and printing it to the console to the players ** // 

public class ReadThread extends Thread {
	private BufferedReader reader; 
	private Socket socket;
	private GameClient client;
	private Player player;
	
	public ReadThread(Socket socket, GameClient client, Player player) {
		this.socket = socket;
		this.client = client;
		this.player = player;
	
		try {
			InputStream input =  socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input));
			
		}catch(IOException ex) {
			System.out.println("Error getting input stream: "+ ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public void run() {		
		 	String response ="";
			while (true) {
			        try {
			        	
			        	response= reader.readLine();
			            if(response.equals("Thread killed")) {
			            	break;
			            }
			            System.out.println("\n" + response);

			        } catch (IOException ex) {
			            System.out.println("Error reading from server: " + ex.getMessage());
			            ex.printStackTrace();
			            break;
			        }
			}
			
	}


}
