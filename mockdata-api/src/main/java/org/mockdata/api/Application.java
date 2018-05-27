package org.mockdata.api;

import com.sun.net.httpserver.HttpServer;
import org.mockdata.api.routes.DataRequestHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application {

    public static void main(String[] args) throws IOException {
        final HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/datarequest", new DataRequestHandler());
        server.setExecutor(null);
        server.start();
    }
}
