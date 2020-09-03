package com.example.subscriptionforme.home;

public class UserSubscriptionData {
    private String startingDate,dDayDate,subscriptionName,subscriptionPrice;
    private int subscriptionImageID;
    private boolean isAlarmOn;

    public UserSubscriptionData(String startingDate, String dDayDate, String subscriptionName, String subscriptionPrice, int subscriptionImageID,boolean isAlarmOn) {
        this.startingDate = startingDate;
        this.dDayDate = dDayDate;
        this.subscriptionName = subscriptionName;
        this.subscriptionPrice = subscriptionPrice;
        this.subscriptionImageID = subscriptionImageID;
        this.isAlarmOn = isAlarmOn;
    }

    public boolean isAlarmOn() {
        return isAlarmOn;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public String getDDayDate() {
        return dDayDate;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public String getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public int getSubscriptionImageID() {
        return subscriptionImageID;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public void setDDayDate(String dDayDate) {
        this.dDayDate = dDayDate;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public void setSubscriptionPrice(String subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }

    public void setSubscriptionImageID(int subscriptionImageID) {
        this.subscriptionImageID = subscriptionImageID;
    }

    public void setAlarmOn(boolean alarmOn) {
        isAlarmOn = alarmOn;
    }

}
