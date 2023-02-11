package com.example.WebSedyolkinYA.dto;

public class LandlordDTO {
    private int id_landlord;
    private String name_landlord;
    private String phone;
    private String email;

    public int getId_landlord() {
        return id_landlord;
    }

    public void setId_landlord(int id_landlord) {
        this.id_landlord = id_landlord;
    }

    public String getName_landlord() {
        return name_landlord;
    }

    public void setName_landlord(String name_landlord) {
        this.name_landlord = name_landlord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
