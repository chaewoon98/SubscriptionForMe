package com.example.subscriptionforme.home;

public class ManagementChartDataVO {
    private String payDate,price;

    public ManagementChartDataVO(String payDate, String price) {
        this.payDate = payDate;
        this.price = price;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
