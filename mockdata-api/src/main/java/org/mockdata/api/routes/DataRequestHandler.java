package org.mockdata.api.routes;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import fi.iki.elonen.NanoHTTPD;
import org.mockdata.api.RequestHandler;
import org.mockdata.api.model.DataRequest;

import java.util.HashMap;
import java.util.Map;

import static fi.iki.elonen.NanoHTTPD.*;

public class DataRequestHandler implements RequestHandler {

    private final Gson gson = new Gson();

    @Override
    public NanoHTTPD.Response serve(final NanoHTTPD.IHTTPSession session) {
        try {
            Map<String, String> files = new HashMap<>();
            session.parseBody(files);

            DataRequest req = gson.fromJson(files.getOrDefault("postData", ""), DataRequest.class);
            String response;
            if (req != null && (response = req.processRequest()) != null) {
                return newFixedLengthResponse(Response.Status.OK, "application/json", response);
            }
        } catch (JsonSyntaxException e) {
            return newFixedLengthResponse(Response.Status.BAD_REQUEST, "application/json", "");
        } catch (Exception ignored) {
        }

        return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "application/json", "");
    }
}
