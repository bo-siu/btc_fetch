package com.zhibt.core;

import com.zhibt.platform.okcoin.OkCoinTradeProxy;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * Created by boris on 14-10-24.
 */
public class Main {

    public static void main(String args[]) {
        String input = null;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        new OkCoinTradeProxy().fetchData();
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
