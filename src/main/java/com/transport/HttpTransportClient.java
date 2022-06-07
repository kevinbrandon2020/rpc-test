package com.transport;

import com.bean.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpTransportClient implements TransportClient {
    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPost();
    }

    @Override
    public InputStream write(InputStream data) {

        try {
           HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.url).openConnection();
           httpURLConnection.setDoOutput(true);
           httpURLConnection.setDoInput(true);
           httpURLConnection.setUseCaches(true);
           httpURLConnection.setRequestMethod("POST");
           httpURLConnection.connect();
           IOUtils.copy(data,httpURLConnection.getOutputStream());
           int code = httpURLConnection.getResponseCode();
           if (code == HttpURLConnection.HTTP_OK) {
               return httpURLConnection.getInputStream();
           }else{
               return httpURLConnection.getErrorStream();
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
