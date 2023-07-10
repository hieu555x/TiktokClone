package com.example.tinderclone.Model;

public class User {
    private String name, registered, location, phone, cell, imageLink, seed;

    public User(String name, String registered, String location, String phone, String cell, String imageLink, String seed) {
        this.name = name;
        this.registered = registered;
        this.location = location;
        this.phone = phone;
        this.cell = cell;
        this.imageLink = imageLink;
        this.seed = seed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }
}
