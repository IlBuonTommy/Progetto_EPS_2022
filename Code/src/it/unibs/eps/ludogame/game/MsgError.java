package it.unibs.eps.ludogame.game;


public enum MsgError {
	UNSUPPORTEDMESSAGETYPE,
	UNSUPPORTEDPROTOCOLVERSION,
	SERVERFULL,
	ILLEGALMOVE,
	NOTYOURTURN,
	UNKNOWN;
	

	@Override
	public String toString() {
		switch(this) {
		case UNSUPPORTEDMESSAGETYPE: return "unsupportedMessageType";
		case UNSUPPORTEDPROTOCOLVERSION: return "unsupportedProtocolVersion";
		case SERVERFULL: return "serverFull";
		case ILLEGALMOVE: return "illegalMove";
		case NOTYOURTURN: return "notYourTurn";
		case UNKNOWN: return "unknown";
		default: return "";
		}
	}
}
