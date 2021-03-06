package com.example.contadordecartas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Cards implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    double manaCost;
    String layout;
    String type;
    String ratity;
    int power;
    int toughness;
    String imageUrl;

    public int getId() {return id;}

    public void setId(int id) { this.id=id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getManaCost() {
        return manaCost;
    }

    public void setManaCost(double manaCost) {
        this.manaCost = manaCost;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRatity() {
        return ratity;
    }

    public void setRatity(String ratity) {
        this.ratity = ratity;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "name='" + name + '\'' +
                ", manaCost=" + manaCost +
                ", layout='" + layout + '\'' +
                ", type='" + type + '\'' +
                ", ratity='" + ratity + '\'' +
                ", power=" + power +
                ", toughness=" + toughness +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
