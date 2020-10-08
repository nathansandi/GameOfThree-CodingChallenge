package com.gameofthree.model;

public class HumanGame {
	private String state;
	private int output;
	private int numerPlayed;
	private boolean isWin;


	public HumanGame() {
		
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getOutput() {
		return output;
	}
	public void setOutput(int output) {
		this.output = output;
	}
	public int getNumerPlayed() {
		return numerPlayed;
	}
	public void setNumerPlayed(int numerPlayed) {
		this.numerPlayed = numerPlayed;
	}	
	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
	@Override
	public String toString() {
		return "HumanGame [state=" + state + ", output=" + output + ", numerPlayerd=" + numerPlayed + "]";
	}
	
	
}
