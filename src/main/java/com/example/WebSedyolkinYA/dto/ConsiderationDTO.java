package com.example.WebSedyolkinYA.dto;

import java.sql.Date;

public class ConsiderationDTO {
    private int id_сonsideration;
    private String name_landlord;
    private String name_game;
    private int number_games;
    private Date delivery_date;

    public int getId_сonsideration() {
        return id_сonsideration;
    }

    public void setId_сonsideration(int id_сonsideration) {
        this.id_сonsideration = id_сonsideration;
    }

    public int getNumber_games() {
        return number_games;
    }

    public void setNumber_games(int number_games) {
        this.number_games = number_games;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getName_landlord() {
        return name_landlord;
    }

    public void setName_landlord(String name_landlord) {
        this.name_landlord = name_landlord;
    }

    public String getName_game() {
        return name_game;
    }

    public void setName_game(String name_game) {
        this.name_game = name_game;
    }
}
