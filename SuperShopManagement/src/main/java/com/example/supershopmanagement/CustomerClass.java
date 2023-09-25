package com.example.supershopmanagement;

public class CustomerClass {
    private String name,contact,lastBuyDate;
    private String lastBuy,totalBuy;

    public CustomerClass(String name,String contact,String lastBuyDate,String lastBuy,String totalBuy){
        this.contact=contact;
        this.lastBuy=lastBuy;
        this.name=name;
        this.lastBuyDate=lastBuyDate;
        this.totalBuy=totalBuy;

    }

    public String getTotalBuy() {
        return totalBuy;
    }

    public void setTotalBuy(String totalBuy) {
        this.totalBuy = totalBuy;
    }

    public String getLastBuy() {
        return lastBuy;
    }

    public void setLastBuy(String lastBuy) {
        this.lastBuy = lastBuy;
    }

    public String getLastBuyDate() {
        return lastBuyDate;
    }

    public void setLastBuyDate(String lastBuyDate) {
        this.lastBuyDate = lastBuyDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
