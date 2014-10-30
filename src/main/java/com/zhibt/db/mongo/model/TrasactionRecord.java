package com.zhibt.db.mongo.model;

import com.mongodb.DBObject;
import org.bson.BSONObject;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Created by boris on 14-10-24.
 */
public class TrasactionRecord implements Serializable, DBImpl {
    public transient static final String COLLECTION_NAME = "trade_data";

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double amount;
    public long date;
    public long tid;
    public double price;
    public String type;


}
