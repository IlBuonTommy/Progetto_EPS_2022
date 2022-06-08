package it.unibs.eps.ludogame.game;


public enum GameState {
	WAITINGFORPLAYERS,
	RUNNING,
	FINISHED;
	
	
	@Override
	public String toString() {
		switch(this) {
		case WAITINGFORPLAYERS: return "waitingForPlayers";
		case RUNNING: return "running";
		case FINISHED: return "finished";
		default: return "";
		}
	}
}
