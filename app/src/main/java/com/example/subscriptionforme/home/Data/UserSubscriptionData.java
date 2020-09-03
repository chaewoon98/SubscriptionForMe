package com.example.subscriptionforme.home.Data;

import java.io.Serializable;

public class UserSubscriptionData implements Serializable {
    private String registerNumber,subscriptionPayDate,subscriptionNumberID,subscriptionName,subscriptionPaymentSystem,subscriptionPrice,alarmSetting,
            isAlarmOn,beginningPayDate,subscriptionDescription,subscriptionDeleteURL;

    private int subscriptionImageID;

    public UserSubscriptionData(String registerNumber, String subscriptionNumberID,String subscriptionName, String subscriptionPaymentSystem
            ,String subscriptionPrice,String beginningPayDate,String subscriptionPayDate,String alarmSetting,String isAlarmOn,String subscriptionDescription,String subscriptionDeleteURL,int subscriptionImageID) {
        this.registerNumber = registerNumber;
        this.subscriptionNumberID = subscriptionNumberID;
        this.subscriptionPayDate = subscriptionPayDate;
        this.beginningPayDate = beginningPayDate;
        this.alarmSetting = alarmSetting;
        this.subscriptionName = subscriptionName;
        this.subscriptionPaymentSystem = subscriptionPaymentSystem;
        this.subscriptionPrice = subscriptionPrice;
        this.isAlarmOn = isAlarmOn;
        this.subscriptionImageID = subscriptionImageID;
        this.subscriptionDescription = subscriptionDescription;
        this.subscriptionDeleteURL = subscriptionDeleteURL;
    }

    public String getSubscriptionDeleteURL() {
        return subscriptionDeleteURL;
    }

    public void setSubscriptionDeleteURL(String subscriptionDeleteURL) {
        this.subscriptionDeleteURL = subscriptionDeleteURL;
    }

    public String getSubscriptionDescription() {
        return subscriptionDescription;
    }

    public void setSubscriptionDescription(String subscriptionDescription) {
        this.subscriptionDescription = subscriptionDescription;
    }

    public String getBeginningPayDate() {
        return beginningPayDate;
    }

    public void setBeginningPayDate(String beginningPayDate) {
        this.beginningPayDate = beginningPayDate;
    }

    public String getIsAlarmOn() {
        return isAlarmOn;
    }

    public void setIsAlarmOn(String isAlarmOn) {
        this.isAlarmOn = isAlarmOn;
    }

    public int getSubscriptionImageID() {
        return subscriptionImageID;
    }

    public void setSubscriptionImageID(int subscriptionImageID) {
        this.subscriptionImageID = subscriptionImageID;
    }

    public String getSubscriptionPayDate() {
        return subscriptionPayDate;
    }

    public void setSubscriptionPayDate(String subscriptionPayDate) {
        this.subscriptionPayDate = subscriptionPayDate;
    }

    public String getAlarmSetting() {
        return alarmSetting;
    }

    public void setAlarmSetting(String alarmSetting) {
        this.alarmSetting = alarmSetting;
    }

    public String getSubscriptionPaymentSystem() {
        return subscriptionPaymentSystem;
    }

    public void setSubscriptionPaymentSystem(String subscriptionPaymentSystem) {
        this.subscriptionPaymentSystem = subscriptionPaymentSystem;
    }

    public String getSubscriptionNumberID() {
        return subscriptionNumberID;
    }

    public void setSubscriptionNumberID(String subscriptionNumberID) {
        this.subscriptionNumberID = subscriptionNumberID;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String isAlarmOn() {
        return isAlarmOn;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public String getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public void setSubscriptionPrice(String subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }

    public void setAlarmOn(String alarmOn) {
        isAlarmOn = alarmOn;
    }

}
