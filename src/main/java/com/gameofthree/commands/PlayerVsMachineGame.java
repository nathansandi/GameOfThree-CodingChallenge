package com.gameofthree.commands;

import java.net.Socket;

import com.gameofthree.GameServer;
import com.gameofthree.gamelogic.GenerateActionBotInput;
import com.gameofthree.gamelogic.GenerateStartNumber;
import com.gameofthree.gamelogic.PlayerInput;
import com.gameofthree.gamelogic.PlayersOutput;
import com.gameofthree.gamelogic.WinnerOutput;
import com.gameofthree.model.Bot;
import com.gameofthree.model.Player;
import com.gameofthree.threads.PlayerSetUp;
import com.gameofthree.threads.PlayerInputManagement;



public class PlayerVsMachineGame {
	private Socket socket; 
	private GameServer server;
	private String status;
	
	public PlayerVsMachineGame(Socket socket, GameServer server) {
		this.socket = socket;
		this.server = server;

	}
    /**
     * Method startNewSinglePlayerGame
     * Methods to start a player game against bot
     * 
     * @param player, thread
     * 
     */
	public void startNewSinglePlayerGame(PlayerSetUp thread, Player player) {
		
		
		//Add myself as a player
		server.addPlayer(player);
				
		//Current Status
		WinnerOutput winnerCheck = new WinnerOutput();
		
		GenerateActionBotInput botPlay = new GenerateActionBotInput();
		PlayersOutput botNewOutput = new PlayersOutput();
		boolean win = false;		
		
		//Generate Bot Player
		Bot botOne = new Bot("BOT").generateName();
	
		
		System.out.println("Bot Generated - Starting the game");
		
		//GeneratNumber 
		GenerateStartNumber startNumber = new GenerateStartNumber();
		int input = startNumber.getStartNumber();
		int numberPlayerd=0;
		server.broadcastTo("Game Mode Bot vs "+player.getName()+" - Started, the number generated is: "+input, thread);
		status=player.getName();
		
		//Round
		while(!win) {
			server.broadcastTo("******TURN : "+status+" Play 1,0 or -1******",thread);
			//Opening thread for the round
			if(status == player.getName()) {
				
				PlayerInput playerTurn = new PlayerInput(socket,server);
				String readPlay= playerTurn.run(player,thread,input,status);
				numberPlayerd =Integer.parseInt(readPlay);
				input = input+numberPlayerd;
			}
			//In case of Bot 
			if(status == botOne.getName()) {
				numberPlayerd = botPlay.getNewInput(input);
				input = input+numberPlayerd;
			}
			
			server.broadcastTo(" Player : "+status+" // played: " +numberPlayerd+ " // result: " +input,thread);
			
			//Change status
			if(status == botOne.getName()) {
				status = player.getName();
			}else {
				status = botOne.getName();
			}	
			//Divide by 3
			input = botNewOutput.sendOutput(input);
			win = winnerCheck.isWin(input);
			if(win) { 
				server.broadcastTo(" Winner: "+status+" //  result: " +input+ " // winner status is: "+win, thread);
				break; 
			}
			//Print the reuslt
			server.broadcastTo(" Player: "+status+" //  received: " +input+ " // winner status is: "+win, thread);
			

		}
 	  
		
	}
	
	
}
