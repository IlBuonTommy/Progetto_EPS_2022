package it.unibs.eps.ludogame.game;

import java.io.Serializable;

public class Giocatore implements Serializable{
    private int colore;
    private String username;

    //Prima di costruire un nuovo giocatore bisogna controllare che il nome
    //e il colore non sia già stato preso da altri giocatori.
    public Giocatore(int colore, String username) {
        this.colore = colore;
        this.username = username;
    }

    public int getColore() {
        return this.colore;
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public String toString() {
        return "{" +
            " colore='" + getColore() + "'" +
            ", username='" + getUsername() + "'" +
            "}";
    }
}