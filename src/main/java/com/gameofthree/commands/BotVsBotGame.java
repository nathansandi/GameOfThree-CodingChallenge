package com.gameofthree.commands;

import java.net.Socket;

import com.gameofthree.GameServer;
import com.gameofthree.gamelogic.GenerateActionBotInput;
import com.gameofthree.gamelogic.GenerateStartNumber;
import com.gameofthree.gamelogic.PlayersOutput;
import com.gameofthree.gamelogic.WinnerOutput;
import com.gameofthree.model.Bot;
import com.gameofthree.model.Player;
import com.gameofthree.threads.PlayerSetUp;
import com.gameofthree.threads.PlayerInputManagement;


public class BotVsBotGame {
	private Socket socket; 
	private GameServer server;
	private String status;
	
	public BotVsBotGame(Socket socket, GameServer server) {
		this.socket = socket;
		this.server = server;

	}
	
	public void StartNewBotGame(PlayerSetUp thread) {

		//Current Status
		WinnerOutput winnerCheck = new WinnerOutput();
		
		GenerateActionBotInput botPlay = new GenerateActionBotInput();
		PlayersOutput botNewOutput = new PlayersOutput();
		boolean win = false;		
		
		//Generate Bot Player
		Bot botOne = new Bot("BOT").generateName();
		Bot botTwo = new Bot("BOT").generateName();
		
		System.out.println("Bots Gerados - iniciando random");
		
		//GeneratNumber 
		GenerateStartNumber startNumber = new GenerateStartNumber();
		int input = startNumber.getStartNumber();
		int numberPlayerd=0;
		server.broadcastTo(botOne.getName()+ " vs "+botTwo.getName() , thread);		
		server.broadcastTo("Game Mode Bot vs Bot - Started, the number generated is: "+input, thread);
		status=botOne.getName();
		
		//Round
		while(!win) {
			//Joga -1 0 ou +1 
			numberPlayerd = botPlay.getNewInput(input);
			input = input+numberPlayerd;
			
			
			//Print the reuslt
			server.broadcastTo(" Machine: "+status+" // played: " +numberPlayerd+ " // result: " +input+ " // winner status is: "+win, thread);
			
			//Muda o Status			
			updateStatus(botOne.getName(), botTwo.getName());
			
			//Divide by 3
			input = botNewOutput.sendOutput(input);
			
			//Checa se é winnable
			win = winnerCheck.isWin(input);
			if(win) { 
				server.broadcastTo(" Machine: "+status+" //  result: " +input+ " // winner status is: "+win, thread);
				break; 
			}
		}
		
	}
	public void updateStatus(String bot1, String bot2) {
		if(status == bot1) {
			status = bot2;
		}else {
			status = bot1;
		}	
		
	};
	
}
