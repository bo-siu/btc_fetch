package com.zhibt.network.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: boris
 * Date: 14-6-17
 * Time: 下午3:12
 * To change this template use File | Settings | File Templates.
 */

public class HTTPRequestWrapper {

    private String mUrl;
    private RequestType mType;
    private long uid;

    //http request parmas
    private List<NameValuePair> mParams;

    //http request header
    private List<NameValuePair> mHeader;

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public List<NameValuePair> getmHeader() {
        return mHeader;
    }

    public void setmHeader(List<NameValuePair> mHeader) {
        this.mHeader = mHeader;
    }

    public RequestType getmType() {
        return mType;
    }

    public void setmType(RequestType mType) {
        this.mType = mType;
    }

    public List<NameValuePair> getmParams() {
        return mParams;
    }

    public void setmParams(List<NameValuePair> mParams) {
        this.mParams = mParams;
    }

    public HttpURLConnection getUrlConnection() {
        return urlConnection;
    }

    public void setUrlConnection(HttpURLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }

    private HttpURLConnection urlConnection;
    private Object data;

    @Override
    public boolean equals(Object obj) {
        if (((HTTPRequestWrapper) (obj)).uid == uid)
            return true;
        else
            return false;
    }

    /**
     * @return read stream
     * @throws java.io.IOException
     */
    public InputStream doProcess() throws IOException {
        FakeX509TrustManager.allowAllSSL();
        URL uri = new URL(mUrl);
        urlConnection = (HttpURLConnection) uri.openConnection();
        urlConnection.setInstanceFollowRedirects(true);
        HttpURLConnection.setFollowRedirects(true);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setConnectTimeout(6000);
        urlConnection.setReadTimeout(60000);
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36)");

        if (mHeader != null) {
            for (NameValuePair pair : mHeader) {
                urlConnection.setRequestProperty(pair.getName(), pair.getValue());
            }
        } else {
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        }

        if (mType == RequestType.POST) {
            StringBuilder builder = new StringBuilder();
            urlConnection.setRequestMethod("POST");
            for (NameValuePair pair : mParams) {
                builder.append(pair.getName()).append("=").append(pair.getValue()).append("&");
            }
            String rs = builder.substring(0, builder.length() - 1);
            urlConnection.getOutputStream().write(rs.getBytes());
        }
        InputStream inputStream = urlConnection.getInputStream();
        return inputStream;

    }


    public enum RequestType {
        GET, POST;
    }
}
