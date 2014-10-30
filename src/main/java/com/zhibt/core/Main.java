package com.zhibt.core;

import com.zhibt.db.mongo.DBUtils;
import com.zhibt.db.mongo.model.DBImpl;
import com.zhibt.db.mongo.model.TrasactionRecord;
import com.zhibt.platform.okcoin.OkCoinTradeProxy;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by boris on 14-10-24.
 */
public class Main {

    public static void main(String args[]) {
        String input = null;
        Thread thread = new Thread(new Runnable() {
            ArrayList<TrasactionRecord> okTradeData;

            @Override
            public void run() {
                while (true) {
                    try {
                        okTradeData = new OkCoinTradeProxy().fetchData();
                        DBUtils.replaceCollections(okTradeData, TrasactionRecord.COLLECTION_NAME, DBUtils.getDB(DBUtils.DB_TRADE_DATA));
                        okTradeData.clear();
                        okTradeData = null;
                        Thread.sleep(25);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        thread.setDaemon(true);
        thread.start();
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                input = reader.readLine();
            } catch (IOException e) {
                Logger.getLogger(Main.class).info("stdio error");
            }
            if (input != null && input.equals("quit")) {
                System.exit(0);
            }

        }
    }
}
