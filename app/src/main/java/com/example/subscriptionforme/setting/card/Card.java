package com.example.subscriptionforme.setting.card;

public class Card{
    String name;
    int image;

    public Card(String name, int imageNumber){
        this.name = name;
        this.image = imageNumber;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setImage(int image){
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public int getImage(){
        return image;
    }
}
