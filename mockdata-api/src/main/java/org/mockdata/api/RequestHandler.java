package org.mockdata.api;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response;

public interface RequestHandler {

    Response serve(NanoHTTPD.IHTTPSession session);

}
