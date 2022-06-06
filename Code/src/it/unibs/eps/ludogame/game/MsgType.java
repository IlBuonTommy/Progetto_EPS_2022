package it.unibs.eps.ludogame.game;


public enum MsgType {
	REGISTER,
	READY,
	MOVE,
	WELCOME,
	ASSIGNCOLOR,
	UPDATE,
	TURN,
	PLAYERDISCONNECTED,
	MESSAGE,
	ERROR;
	
	@Override
	public String toString() {
		switch(this) {
		case REGISTER: return "register";
		case READY: return "ready";
		case MOVE: return "move";
		case WELCOME: return "welcome";
		case ASSIGNCOLOR: return "assignColor";
		case UPDATE: return "update";
		case TURN: return "turn";
		case PLAYERDISCONNECTED: return "playerDisconnected";
		case MESSAGE: return "message";
		case ERROR: return "error";
		default: return "";
		}
	}
}
