package it.unibs.eps.ludogame.game;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Enum che definisce il colore dei giocatori e li associa ai numeri
 *
 */
public enum PlayerColor {
	RED(0),
	BLUE(1),
	GREEN(2),
	YELLOW(3);

	/**
	 * converte gli enum in toString
	 */
	@Override
	public String toString() {
		switch(this) {
		case RED: return "red";
		case BLUE: return "blue";
		case GREEN: return "green";
		case YELLOW: return "yellow";
		default: return "";
		}
	}

	private int value;
    private static Map<Integer, PlayerColor> map = new HashMap<Integer, PlayerColor>();
    private static Boolean[] availColor = {true, true, true, true};
    
    static {
        for (PlayerColor PlayerColor : PlayerColor.values()) {
            map.put(PlayerColor.value, PlayerColor);
        }
    }

    /**
     * costruttore del colore
     * @param value
     */
    private PlayerColor(Integer value) {
        this.value = value;
    }

    /**
     * ritorna il colore di un singolo valore
     * @param PlayerColor
     * @return
     */
    public static PlayerColor valueOf(Integer PlayerColor) {
        return (PlayerColor) map.get(PlayerColor);
    }

  /**
   *
   * @return il valore di un singolo colore
   */
    public Integer getValue() {
        return value;
    }
   
    

    /**
     * verifica la disponibilità del colore
     * @param requestedColor
     * @return il colore
     */
    public static PlayerColor getAvail(PlayerColor requestedColor) {
    	if (requestedColor != null && availColor[requestedColor.getValue()]) {
    		availColor[requestedColor.getValue()] = false;
    		return requestedColor;
    	}
    	else {
    		for (Integer i = 0; i < availColor.length; i++) {
    			if (availColor[i]) {
    				availColor[i] = false;
    				return PlayerColor.valueOf(i);
    			}
    		}
        	return null;
    	}
    }

    /**
     * rende un colore disponibile
     * @param add
     */
    public static void setAvail(PlayerColor add) {
    	availColor[add.getValue()] = true;
    }

    /**
     * resetta la disponibilità del colore
     */
    public static void resetAvail() {
    	for (Integer i = 0; i < availColor.length; i++) {
    		availColor[i] = true;
    	}
    }
}
