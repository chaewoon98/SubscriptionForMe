package com.example.subscriptionforme.setting.card;

// 계좌 데이터
public class AccountVO {

    private String resAccountTrDate; // 결제 날짜 ex) 20200805  yyyy/mm/dd
    private String resAccountTrTime; // 결제 시간 ex) 130812 hh/mm/ss
    private String resAccountOut; // 출금 ex) 50000
    private String resAccountIn; // 입금
    private String resAccountDesc1; // 입금자/받는 사람
    private String resAccountDesc2; // 설명
    private String resAccountDesc3; // 기관 ex) 네이버페이 결제
    private String resAfterTranBalance; // 통장 잔고 ex) 300000

    public AccountVO(){
        resAccountTrDate = "";
        resAccountTrTime = "";
        resAccountIn = "";
        resAccountOut = "";
        resAccountDesc1 = "";
        resAccountDesc2 = "";
        resAccountDesc3 = "";
        resAfterTranBalance = "";
    }

    public AccountVO(String resAccountTrDate, String resAccountTrTime, String resAccountOut, String resAccountIn, String resAccountDesc1, String resAccountDesc2, String resAccountDesc3, String resAfterTranBalance){
        this.resAccountTrDate = resAccountTrDate;
        this.resAccountTrTime = resAccountTrTime;
        this.resAccountOut = resAccountOut;
        this.resAccountIn = resAccountIn;
        this.resAccountDesc1 = resAccountDesc1;
        this.resAccountDesc2 = resAccountDesc2;
        this.resAccountDesc3 = resAccountDesc3;
        this.resAfterTranBalance = resAfterTranBalance;
    }


    public String getResAfterTranBalance() {
        return resAfterTranBalance;
    }

    public void setResAfterTranBalance(String resAfterTranBalance) {
        this.resAfterTranBalance = resAfterTranBalance;
    }

    public String getResAccountDesc3() {
        return resAccountDesc3;
    }

    public void setResAccountDesc3(String resAccountDesc3) {
        this.resAccountDesc3 = resAccountDesc3;
    }

    public String getResAccountDesc2() {
        return resAccountDesc2;
    }

    public void setResAccountDesc2(String resAccountDesc2) {
        this.resAccountDesc2 = resAccountDesc2;
    }

    public String getResAccountDesc1() {
        return resAccountDesc1;
    }

    public void setResAccountDesc1(String resAccountDesc1) {
        this.resAccountDesc1 = resAccountDesc1;
    }

    public String getResAccountIn() {
        return resAccountIn;
    }

    public void setResAccountIn(String resAccountIn) {
        this.resAccountIn = resAccountIn;
    }

    public String getResAccountOut() {
        return resAccountOut;
    }

    public void setResAccountOut(String resAccountOut) {
        this.resAccountOut = resAccountOut;
    }



    public String getResAccountTrDate() {
        return resAccountTrDate;
    }

    public void setResAccountTrDate(String resAccountTrDate) {
        this.resAccountTrDate = resAccountTrDate;
    }

    public String getResAccountTrTime() {
        return resAccountTrTime;
    }

    public void setResAccountTrTime(String resAccountTrTime) {
        this.resAccountTrTime = resAccountTrTime;
    }
}
