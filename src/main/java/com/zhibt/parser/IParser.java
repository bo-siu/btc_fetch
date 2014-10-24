package com.zhibt.parser;

import java.io.InputStream;

/**
 * @author boris
 */
public interface IParser {

    /**
     * @param in
     */
    public Object parse(InputStream in);

    public int getCode();

    public String getMsg();
}
