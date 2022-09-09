package it.unibs.eps.ludogame.networking;

import java.io.Serializable;
/**
 * 
 * classe che serve per incapsulare dati da scambiare tra server e client e viceversa
 *
 */
public class Pacchetto implements Serializable{
	private String type;
	private Object message;
	
	public Pacchetto(String type, Object message) {
		this.type = type;
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
		return "Pacchetto [type=" + type + ", message=" + message + "]";
	}
	
	
	
	

}
