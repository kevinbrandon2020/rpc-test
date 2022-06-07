package com.transport;

import com.bean.RequestHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpTransportServer implements TransportServer{
    private Server server;
    private RequestHandler handler;



    @Override
    public void init(int port, RequestHandler toResp) {
        this.server = new Server(port);
        this.handler = toResp;

        //接收消息
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        //网络请求抽象
        ServletHolder servletHolder = new ServletHolder(new RequestServlet());
        ctx.addServlet(servletHolder, "/*");


    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class RequestServlet extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
            ServletInputStream in = req.getInputStream();
            ServletOutputStream out = resp.getOutputStream();
            if (handler != null){
                handler.onRequest(in, out);
            }
            out.flush();
        }
    }
}
