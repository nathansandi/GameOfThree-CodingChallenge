package com.gameofthree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import com.gameofthree.gamelogic.GenerateStartNumber;
import com.gameofthree.gamelogic.WinnerOutput;
import com.gameofthree.model.HumanGame;
import com.gameofthree.model.Player;
import com.gameofthree.threads.PlayerSetUp;
import com.gameofthree.utils.PropertiesConfig;

public class GameServer {
	static {initConfigLoader();}
	private static final String PORT = PropertiesConfig.getProperties().getProperty("com.gameofthree.server.port");
	private static final String MAX_CON = PropertiesConfig.getProperties().getProperty("com.gameofthree.server.max_con");
	private static int port = Integer.parseInt(PORT);
	private ArrayList<Player> players = new ArrayList<>();
	private Set<PlayerSetUp> playerSetUp = new HashSet<>();


	HumanGame humanGame;

	public GameServer(int port) {
		this.port=port;
	}
	
	private static void initConfigLoader() {
		//Initialize Global Config 
    	PropertiesConfig.initialize("application.properties");
	}

	public void execute() {
		try(ServerSocket serverSocket = new ServerSocket(port)){
			System.out.println("Server is runing and listening the port "+port);
			
			while(true) {
				if(playerSetUp.size() < Integer.parseInt(MAX_CON)){
					Socket socket = serverSocket.accept();
					System.out.println("New user connected");
					
					PlayerSetUp newPlayer = new PlayerSetUp(socket,this);
					playerSetUp.add(newPlayer);
					newPlayer.start();
				}
					
			}
			
		} catch (IOException e) {
			 System.out.println("Error in the server: " + e.getMessage());
	         e.printStackTrace();
		}
		
	}
	//Return players
	public ArrayList<Player> getAllPlayers() {
		return players;
	}
	
	
	//Broadcast to all users
    public void broadcast(String message, PlayerSetUp excludeUser) {
	        for (PlayerSetUp aPlayer : playerSetUp) {
	            if (aPlayer != excludeUser) {
	            	aPlayer.sendMessage(message);
	            }
	        }
	}
    //Mesasge to specific user 
    public void broadcastTo(String message, PlayerSetUp User) {
        for (PlayerSetUp aPlayer : playerSetUp) {
            if (aPlayer == User) {
            	aPlayer.sendMessage(message);
            }
        }
    }

	public boolean checkPlayersConnections() {
		//System.out.println("Players connecteds_ - "+players.size()  );
		if(players.size() == 2) {
			return true;
		}
		return false;
	        
	}  
    
	public void addPlayer(Player player) {
		players.add(player);
	        
	}  
	
	//Remove player thread + player from the list of players 
	public void removePlayer(Player playerToRemove, PlayerSetUp playerThreadRemove) {
		boolean  removed=false;
		System.out.println("players "+players.toString());
		for(int i = 0 ; i<players.size();i++) {
			if(players.get(i).getName() == playerToRemove.getName())
				
				if(players.remove(players.get(i))) {
					removed=true;
				}
		}

		playerSetUp.remove(playerThreadRemove);
		System.out.println("The player quitted");

	}

    //Returns true if there are other users connected    
    boolean hasUsers() {
        return !this.players.isEmpty();
    }
    //Logic - Human Game 
    public synchronized HumanGame gameLogic(HumanGame playerMove) {
    	WinnerOutput winnerCheck = new WinnerOutput();
    	humanGame = playerMove;
    	//Check if is winner
    	humanGame.setWin(winnerCheck.isWin(humanGame.getNumerPlayed()));
    	//Change Status     	
    	if(humanGame.getState() == players.get(0).getName()) {
    		humanGame.setState(players.get(1).getName());
    		
		}else {
			humanGame.setState(players.get(0).getName());
		}	
    	
    	
    	return humanGame;    	
    }    
    //Get Game State
    public HumanGame getGame() {		  	
    	return humanGame;    	
    }
    
    //Setup a new human game
    public HumanGame newGame(Player player) {
		//Configure when the first play connect    	
    	if(players.size() == 1) {
    		GenerateStartNumber newNumber = new GenerateStartNumber();    		
    		humanGame = new HumanGame();
    		humanGame.setNumerPlayed(newNumber.getStartNumber());
    		humanGame.setOutput(0);
    		humanGame.setState(player.getName());
    		humanGame.setWin(false);
    	}
    	//For the second player, return current game 
    	return humanGame;
    	
    }
    
   
    public static void main(String[] args) {    
    	//Start server in the port 
        GameServer server = new GameServer(port);    
        server.execute();
    }
   
    
 
	
}
