package org.mockdata.api;

import fi.iki.elonen.NanoHTTPD;
import org.mockdata.api.routes.DataRequestHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Application extends NanoHTTPD {

    private Map<String, RequestHandler> getRoutes = new HashMap<>();
    private Map<String, RequestHandler> postRoutes = new HashMap<>();

    public Application() throws IOException {
        super(8000);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    public static void main(String[] args) throws IOException {
        Application app = new Application();
        app.postRoutes.put("/datarequest", new DataRequestHandler());
    }

    @Override
    public Response serve(IHTTPSession session) {
        RequestHandler handler = null;

        if (session.getMethod() == Method.POST) {
            handler = postRoutes.get(session.getUri());
        } else if (session.getMethod() == Method.GET) {
            handler = getRoutes.get(session.getUri());
        }

        if (handler == null && postRoutes.get(session.getUri()) != null && getRoutes.get(session.getUri()) != null) {
            return newFixedLengthResponse(Response.Status.METHOD_NOT_ALLOWED, "application/json", "");
        } else if (handler == null) {
            return newFixedLengthResponse(Response.Status.NOT_FOUND, "application/json", "");
        }

        Response response = handler.serve(session);
        response.addHeader("Access-Control-Allow-Origin", "*");

        return response;
    }
}
