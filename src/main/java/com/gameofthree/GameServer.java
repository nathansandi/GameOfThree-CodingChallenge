package com.gameofthree;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

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
	private static final AtomicInteger nameCounter = new AtomicInteger(1);

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
			
			while(playerSetUp.size() < Integer.parseInt(MAX_CON)) {
					Socket socket = serverSocket.accept();
					System.out.println("New user connected");					
					PlayerSetUp newPlayer = new PlayerSetUp(socket,this);
					playerSetUp.add(newPlayer);					
					newPlayer.start();
			}
			
		} catch (IOException e) {
			 System.out.println("Error in the server: " + e.getMessage());
	         e.printStackTrace();
		}
		
	}
    /**
     * Method getAllPlayers
     * Methods to return all active players
     * 
     * @return players
     * 
     */
	public ArrayList<Player> getAllPlayers() {
		return players;
	}
	
	
    /**
     * Method broadcast, broadcastTo
     * Methods to send message from the server to the players
     * 
     * @param message, player
     * 
     */
    public void broadcast(String message, PlayerSetUp excludeUser) {
	        for (PlayerSetUp aPlayer : playerSetUp) {
	            if (aPlayer != excludeUser) {
	            	aPlayer.sendMessage(message);
	            }
	        }
	}
    public void broadcastTo(String message, PlayerSetUp User) {
        for (PlayerSetUp aPlayer : playerSetUp) {
            if (aPlayer == User) {
            	aPlayer.sendMessage(message);
            }
        }
    }
    
    //Check if there are 2 players connected in the server
	public boolean checkPlayersConnections() {
		if(players.size() == 2) {
			return true;
		}
		return false;      
	}  
    /**
     * Method addPlayer
     * Add player to the list of players in case of any player game mode
     * 
     * @param playerToRemove
     * 
     */
	public void addPlayer(Player player) {
		players.add(player);
	        
	}  
    /**
     * Method removePlayer
     * Remove player and active thread from the list
     * 
     * @param playerToRemove - Player model to remove // playerThreadRemove - Thread to remove
     * 
     */
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
    
    /**
     * Method gameLogic 
     * Method to update multiplayer game status
     * 
     * @param playerMove - humanGame with the data of player turn
     * @return humanGame updated
     */
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
    /**
     * Method getGame
     * 
     * @Return multiplayer game running on the server.
     * 
     */
    public HumanGame getGame() {		  	
    	return humanGame;    	
    }
    
    /**
     * Method newGame.
     * Start client threads. Restrictions: If there are more than 2 players, server will close the socket for the 3rd player.
     * 
     * @param player 
     * @Return new multiplayer game
     */
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
