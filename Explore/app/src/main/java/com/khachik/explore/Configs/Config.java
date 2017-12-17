package com.khachik.explore.Configs;

public class Config {
    private String host;
    private int port;
    public Config() {
//        this.host = "192.168.4.221";
//        this.port = 4300;
        this.host = "ec2-13-59-235-170.us-east-2.compute.amazonaws.com";
        this.port = 4300;
    }
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }


}
