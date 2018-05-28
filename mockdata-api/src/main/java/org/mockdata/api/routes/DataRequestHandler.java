package org.mockdata.api.routes;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.http.protocol.HTTP;
import org.mockdata.api.model.DataRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataRequestHandler implements HttpHandler {

    private final Gson gson = new Gson();

    @Override
    public void handle(final HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");

        if (httpExchange.getRequestMethod().equalsIgnoreCase("POST")) {
            final String body = readBody(httpExchange.getRequestBody());

            try {
                final DataRequest req = gson.fromJson(body, DataRequest.class);
                String response;
                if (req == null || (response = req.processRequest()) == null) {
                    httpExchange.sendResponseHeaders(422, -1);
                } else {
                    httpExchange.sendResponseHeaders(200, response.length());
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } catch (JsonSyntaxException e) {
                httpExchange.sendResponseHeaders(400, -1);
            }

        } else {
            httpExchange.sendResponseHeaders(405, -1);
        }
        httpExchange.close();
    }

    private static String readBody(final InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buf = new byte[2048];
        int read;

        while ((read = in.read(buf)) != -1) {
            out.write(buf, 0, read);
        }
        return new String(out.toByteArray());
    }
}
