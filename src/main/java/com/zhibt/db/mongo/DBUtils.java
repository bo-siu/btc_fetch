package com.zhibt.db.mongo;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import com.zhibt.db.mongo.model.DBImpl;
import org.apache.log4j.Logger;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by boris on 14-10-24.
 */
public class DBUtils {

    public static final String DB_TRADE_DATA = "db_trade_data";

    private static MongoClient mongoClient;

    private DBUtils() {
    }

    public synchronized static MongoClient getDBClient() {
        if (mongoClient == null) {
            try {
                mongoClient = new MongoClient("localhost");
            } catch (UnknownHostException e) {
                Logger.getLogger(DBUtils.class).error(e);
            }
        }
        return mongoClient;
    }

    public static DB getDB(String name) {
        return getDBClient().getDB(name);
    }


    public static <T> void replaceCollections(ArrayList<T> datas, String collecName, DB db) {
        DBCollection collection = db.getCollection(collecName);
        Gson gson = new Gson();
        for (T data : datas) {
            collection.save((DBObject) JSON.parse(gson.toJson(data)));
        }
    }

}
