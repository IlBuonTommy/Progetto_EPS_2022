package it.unibs.eps.ludogame.game;

import java.io.Serializable;

public class Giocatore implements Serializable{
    private int colore;
    private String username;
    private boolean bot;

    //Prima di costruire un nuovo giocatore bisogna controllare che il nome
    //e il colore non sia già stato preso da altri giocatori.
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