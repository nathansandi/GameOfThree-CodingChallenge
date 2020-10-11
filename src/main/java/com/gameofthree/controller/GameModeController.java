package com.gameofthree.controller;

import java.io.IOException;
import java.net.Socket;

import com.gameofthree.GameServer;
import com.gameofthree.commands.BotVsBotGame;
import com.gameofthree.commands.Exit;
import com.gameofthree.commands.PlayerVsMachineGame;
import com.gameofthree.commands.PlayerVsPlayerGame;
import com.gameofthree.model.Player;
import com.gameofthree.threads.PlayerSetUp;

public class GameModeController {
	private Socket socket;
	private GameServer server;
	
	
	public GameModeController(Socket socket, GameServer server) {
		this.socket = socket;
		this.server = server;
	}
    /**
     * Method gameModeStarter
     * Menu controller
     * 
     * @param gameMode,thread(PlayerSetUp), player
     * 
     */ 
	public void gameModeStarter(String gameMode, PlayerSetUp playerSetUp, Player Player){
		   switch (gameMode) {
           case "1":
        	   System.out.println("***Starting Bot vs Bot mode***");
        	   new BotVsBotGame(socket,server).startNewBotGame(playerSetUp);
       	 	   new Exit(server, socket).quitGame(playerSetUp,Player);
               break;
           case "2":
        	   System.out.println("***Starting Human vs Bot mode***");
        	   new PlayerVsMachineGame(socket,server).startNewSinglePlayerGame(playerSetUp, Player);
        	   new Exit(server, socket).quitGame(playerSetUp,Player);
        	   break;
           case "3":
        	   System.out.println("***Starting Human vs Human mode***");
			   new PlayerVsPlayerGame(socket,server).startNewPlayerVsPlayerGame(playerSetUp, Player);
			   new Exit(server, socket).quitGame(playerSetUp,Player);
			   break;
           case "Quit":
        	   new Exit(server, socket).quitGame(playerSetUp,Player);
        	   break;
           default:
               server.broadcastTo("Invalid Mode", playerSetUp);
       }
	}
	
}
