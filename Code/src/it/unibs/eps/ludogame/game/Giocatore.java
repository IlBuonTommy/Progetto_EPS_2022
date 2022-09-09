package it.unibs.eps.ludogame.game;

import java.io.Serializable;
/**
 * implementa le caratteristiche del giocatore
 * 
 *
 */
public class Giocatore implements Serializable{
    private int colore;
    private String username;
    private boolean bot;

    public Giocatore(int colore, String username, Boolean bot) {
        this.colore = colore;
        this.username = username;
        this.bot = bot;
    }

    public int getColore() {
        return this.colore;
    }

    public boolean getBot() {
        return this.bot;
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public String toString() {
        return "{" +
            " colore='" + getColore() + "'" +
            ", username='" + getUsername() + "'" +
            ", bot='" + getBot() + "'" +
            "}";
    }
}