package com.zhibt.parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author boris
 */
public abstract class BaseParser implements IParser {
    public static final String TAG = "BaseParser";

    protected int code = -1;
    protected String msg = "";

    protected abstract Object parse(String jsonString) throws JSONException;

    @Override
    public int getCode() {
        return code;
    }

    protected void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    protected void setMsg(String msg) {
        this.msg = msg;
    }

    public Object parse(InputStream in) {
        try {
            if (null == in) {
                return null;
            }
            String jsonString = inputStream2String(in);

            if (jsonString == null || jsonString.equals("")) {
                return null;
            } else {
                return parse(jsonString);
            }
        } catch (IOException e) {
        } catch (JSONException e) {
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
        return null;
    }

    /**
     * 返回的json是"status":{ "code":"0", "msg":"成功" }, "data":{}的格式时
     * 使用该法解析出code，判断code为成功时，返回data的内容
     *
     * @param jsonString
     * @return
     * @throws org.json.JSONException
     */
    protected void parseDataContent(String jsonString) throws JSONException {
        JSONObject result = new JSONObject(jsonString);
        JSONObject status = result.getJSONObject("status");
        if (status != null) {
            code = status.optInt("code", -1);
            msg = status.optString("msg", "");

        }
    }

    public static String inputStream2String(InputStream in) throws IOException {
        if (in == null)
            return "";

        final int size = 128;
        byte[] buffer = new byte[size];

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int cnt = 0;
        while ((cnt = in.read(buffer)) > -1) {
            baos.write(buffer, 0, cnt);
        }
        baos.flush();

        in.close();
        baos.close();

        return baos.toString();
    }
}
