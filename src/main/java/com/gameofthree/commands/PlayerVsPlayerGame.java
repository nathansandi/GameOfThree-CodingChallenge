package com.gameofthree.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.gameofthree.GameServer;
import com.gameofthree.gamelogic.GenerateStartNumber;
import com.gameofthree.gamelogic.PlayerInput;
import com.gameofthree.gamelogic.PlayersOutput;
import com.gameofthree.gamelogic.WinnerOutput;
import com.gameofthree.model.HumanGame;
import com.gameofthree.model.Player;
import com.gameofthree.threads.PlayerSetUp;


public class PlayerVsPlayerGame{
	private Socket socket; 
	private GameServer server;
	int i = 0 ;
	private ArrayList<Player> players = new ArrayList<>(); 
    private BufferedReader in;
    private PrintWriter out;
	private int GeneratedNumber;
	String status;
	int input=0;
	int output;

	
	public PlayerVsPlayerGame(Socket socket, GameServer server) {
		this.socket = socket;
		this.server = server;

	}
	
	public synchronized  void StartNewPlayerVsPlayerGame(PlayerSetUp thread, Player player) {
		
		PlayersOutput setNewNumber = new PlayersOutput();
		System.out.println("**connected -  player: " + player.getName() + " Na thread "+thread.getName());
		
		//Add myself as a player
		server.addPlayer(player);
		//Setup a new Game
		HumanGame humanGame = server.newGame(player);		
		//Waiting for a new Player 
		server.broadcastTo("Waiting for the new player.", thread);
		while(!server.checkPlayersConnections()){
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Ready to interact 		
		server.broadcastTo("****NEW GAME STARTED***", thread);
		server.broadcastTo("Number Generated " +humanGame.getNumerPlayed()+ "// player " +humanGame.getState()+ " starts" ,thread);
		while(!humanGame.isWin()) {
			//Get Game updated 
	
			humanGame = server.getGame();
			//Get data from the game 		
			System.out.println("Human game - "+humanGame);
	
			//Read player input
			PlayerInput playerTurn = new PlayerInput(socket,server);
			String receivePlayerMove = playerTurn.run(player,thread,humanGame.getNumerPlayed(),humanGame.getState());

			if(!server.getGame().isWin()) {
				int playerMove = Integer.parseInt(receivePlayerMove);
				//Set output
				humanGame.setOutput(playerMove);	
				//Set new Number after divide
				humanGame.setNumerPlayed(setNewNumber.sendOutput(playerMove+humanGame.getNumerPlayed()));
				
				server.broadcast("***Turn Results***",thread);
				//Wake up second thread and change turn		
				
				
				//Send to Server process the turn 
				humanGame = server.gameLogic(humanGame);
				server.broadcast(" Player : "+player.getName()+" // Number received: " +humanGame.getNumerPlayed()+ " // Last round output: " +humanGame.getOutput() + "// win: "+humanGame.isWin(),thread);
	
				
			}
		}
		
		

	}
}
	
	

