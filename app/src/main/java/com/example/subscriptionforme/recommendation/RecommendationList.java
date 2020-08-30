package com.example.subscriptionforme.recommendation;

public class RecommendationList {

    private String title;  //관련 서비스
    private String name;  //서비스 이름
    private String price;  //서비스 가격
    private String consumption;  //사용자의 관련 소비 금액
    private String discount;  //할인금액
    private int icon;
    private int color;
    private int benefit;

    public RecommendationList(String title, String name, String price, String consumption, String discount, int icon, int color, int benefit){
        this.title = title;
        this.name = name;
        this.price = price;
        this.consumption = consumption;
        this.discount = discount;
        this.icon = icon;
        this.color = color;
        this.benefit = benefit;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setBenefit(int benefit) { this.benefit = benefit; }

    public int getBenefit() { return benefit; }
}
