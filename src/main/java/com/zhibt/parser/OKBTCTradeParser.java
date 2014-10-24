package com.zhibt.parser;

import com.zhibt.db.mongo.model.TrasactionRecord;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author boris
 */
public class OKBTCTradeParser extends BaseParser {
    @Override
    public Object parse(String jsonString) throws JSONException {
        Logger.getLogger(this.getClass()).info(jsonString);
        JSONArray array = new JSONArray(jsonString);
        ArrayList<TrasactionRecord> datas = new ArrayList<TrasactionRecord>();
        for (int i = 0; i < array.length(); i++) {
            TrasactionRecord data = new TrasactionRecord();
            JSONObject object = array.getJSONObject(i);
            data.price = object.optDouble("price", 0);
            data.date = object.optLong("date", 0);
            data.amount = object.optDouble("amount", 0);
            data.tid = object.optLong("tid", 0);
            data.type = object.optString("type");
            datas.add(data);
        }
        return datas;
    }
}
