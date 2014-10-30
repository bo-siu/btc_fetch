package com.zhibt.platform.okcoin;

import com.zhibt.db.mongo.model.TrasactionRecord;
import com.zhibt.network.http.HTTPRequestWrapper;
import com.zhibt.parser.OKBTCTradeParser;
import com.zhibt.platform.FetchTradeDataProxy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by boris on 14-10-25.
 */
public class OkCoinTradeProxy extends FetchTradeDataProxy {

    public static final String URL_TRADES = "https://www.okcoin.cn/api/trades.do?since=%s";
    public static int sleep = 25;
    private static long id = -1;

    @Override
    public ArrayList<TrasactionRecord> fetchData() throws IOException {
        HTTPRequestWrapper wrapper = new HTTPRequestWrapper();
        if (id == -1) {
            wrapper.setmUrl(String.format(URL_TRADES, ""));
        } else
            wrapper.setmUrl(String.format(URL_TRADES, id));

        ArrayList<TrasactionRecord> datas = (ArrayList<TrasactionRecord>) new OKBTCTradeParser().parse(wrapper.doProcess());
        for (int i = 0; i < datas.size(); i++) {
            TrasactionRecord data = datas.get(i);
            StringBuffer buffer = new StringBuffer();
        }
        id = datas.get(datas.size() - 1).tid;
        return datas;
    }

    public static String format(long times) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(times);
    }
}
