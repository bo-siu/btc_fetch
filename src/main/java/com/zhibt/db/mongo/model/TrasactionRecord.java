package com.zhibt.db.mongo.model;

/**
 * Created by boris on 14-10-24.
 */
public class TrasactionRecord {
    public double amount;
    public long date;
    public long tid;
    public double price;
    public String type;


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
