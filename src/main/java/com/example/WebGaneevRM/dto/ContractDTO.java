package com.example.WebGaneevRM.dto;

import java.sql.Date;

public class ContractDTO {
    private int id_сontract;
    private String name_renter;
    private String name_landlord;
    private String name_game;
    private Date conclusion_date;
    private Date time_line;
    private float amount;

    public int getId_сontract() {
        return id_сontract;
    }

    public void setId_сontract(int id_сontract) {
        this.id_сontract = id_сontract;
    }

    public String getName_renter() {
        return name_renter;
    }

    public void setName_renter(String name_renter) {
        this.name_renter = name_renter;
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

    public Date getConclusion_date() {
        return conclusion_date;
    }

    public void setConclusion_date(Date conclusion_date) {
        this.conclusion_date = conclusion_date;
    }

    public Date getTime_line() {
        return time_line;
    }

    public void setTime_line(Date time_line) {
        this.time_line = time_line;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
