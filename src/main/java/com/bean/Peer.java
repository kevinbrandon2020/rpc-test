package com.bean;

public class Peer {
    private String host;
    private int post;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public Peer() {
    }

    public Peer(String host, int post) {
        this.host = host;
        this.post = post;
    }
}
