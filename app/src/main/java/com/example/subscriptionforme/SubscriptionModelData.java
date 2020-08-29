package com.example.subscriptionforme;

import android.os.Parcel;
import android.os.Parcelable;

public class SubscriptionModelData implements Parcelable {
    private String name,paymentSystem,paymentCycle,price,descrpition,registrationUrl,cancellationUrl;
    private int imageID;

    public SubscriptionModelData(String name, String paymentSystem, String paymentCycle, String price, String descrpition, String registrationUrl, String cancellationUrl, int imageID) {
        this.name = name;
        this.paymentSystem = paymentSystem;
        this.paymentCycle = paymentCycle;
        this.price = price;
        this.descrpition = descrpition;
        this.registrationUrl = registrationUrl;
        this.cancellationUrl = cancellationUrl;
        this.imageID = imageID;
    }

    protected SubscriptionModelData(Parcel in) {
        name = in.readString();
        paymentSystem = in.readString();
        paymentCycle = in.readString();
        price = in.readString();
        descrpition = in.readString();
        registrationUrl = in.readString();
        cancellationUrl = in.readString();
        imageID = in.readInt();
    }

    public static final Creator<SubscriptionModelData> CREATOR = new Creator<SubscriptionModelData>() {
        @Override
        public SubscriptionModelData createFromParcel(Parcel in) {
            return new SubscriptionModelData(in);
        }

        @Override
        public SubscriptionModelData[] newArray(int size) {
            return new SubscriptionModelData[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentSystem() {
        return paymentSystem;
    }

    public void setPaymentSystem(String paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public String getPaymentCycle() {
        return paymentCycle;
    }

    public void setPaymentCycle(String paymentCycle) {
        this.paymentCycle = paymentCycle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescrpition() {
        return descrpition;
    }

    public void setDescrpition(String descrpition) {
        this.descrpition = descrpition;
    }

    public String getRegistrationUrl() {
        return registrationUrl;
    }

    public void setRegistrationUrl(String registrationUrl) {
        this.registrationUrl = registrationUrl;
    }

    public String getCancellationUrl() {
        return cancellationUrl;
    }

    public void setCancellationUrl(String cancellationUrl) {
        this.cancellationUrl = cancellationUrl;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(paymentSystem);
        parcel.writeString(paymentCycle);
        parcel.writeString(price);
        parcel.writeString(descrpition);
        parcel.writeString(registrationUrl);
        parcel.writeString(cancellationUrl);
        parcel.writeInt(imageID);
    }
}

