package com.terwergreen.util;

/**
 * @author: terwer
 * @date: 2021/12/16 00:24
 * @description:
 */
public class ConnectionFactory {
    private static ConnectionUtils connectionUtils = null;

    private ConnectionFactory() {

    }

    public static ConnectionUtils getInstance() {
        if (connectionUtils == null) {
            synchronized (ConnectionFactory.class) {
                if (connectionUtils == null) {
                    connectionUtils = new ConnectionUtils();
                }
            }
        }
        return connectionUtils;
    }
}
