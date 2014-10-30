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


    public static void replaceCollections(ArrayList<DBImpl> datas, String name, DB db) {
        DBCollection collection = db.getCollection(name);
        Gson gson = new Gson();
        for (DBImpl data : datas) {
            collection.save((DBObject) JSON.parse(gson.toJson(data)));
        }
    }

}
