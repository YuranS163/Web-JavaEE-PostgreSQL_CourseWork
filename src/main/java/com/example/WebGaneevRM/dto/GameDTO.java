package com.example.WebGaneevRM.dto;

import java.sql.Date;

public class GameDTO {
    private int id_game;
    private String name_game;
    private Date release_date;
    private int number_players;
    private String difficulty;

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public String getName_game() {
        return name_game;
    }

    public void setName_game(String name_game) {
        this.name_game = name_game;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public int getNumber_players() {
        return number_players;
    }

    public void setNumber_players(int number_players) {
        this.number_players = number_players;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
