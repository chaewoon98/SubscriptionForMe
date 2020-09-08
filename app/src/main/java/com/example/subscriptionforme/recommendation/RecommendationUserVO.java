package com.example.subscriptionforme.recommendation;

public class RecommendationUserVO {
    private int icon,color;
    private String title,name,description;

    public RecommendationUserVO(String title, String name, String description, int icon,int color) {
        this.icon = icon;
        this.title = title;
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
