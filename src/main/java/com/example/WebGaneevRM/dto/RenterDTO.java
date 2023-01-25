package com.example.WebGaneevRM.dto;

public class RenterDTO {
    private int id_renter;
    private String name_renter;
    private String phone;
    private String email;

    public int getId_renter() {
        return id_renter;
    }

    public void setId_renter(int id_renter) {
        this.id_renter = id_renter;
    }

    public String getName_renter() {
        return name_renter;
    }

    public void setName_renter(String name_renter) {
        this.name_renter = name_renter;
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
