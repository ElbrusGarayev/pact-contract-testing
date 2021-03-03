package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

/**
 * @author Elbrus Garayev on 2/2/2021
 */
public class RestService {

    private static final String server = "http://localhost:8080";

    public static void get(HttpHeaders httpHeaders, String path) throws IOException {
        HttpRequestFactory request = new NetHttpTransport().createRequestFactory();
        HttpRequest httpRequest = request.buildGetRequest(new GenericUrl(server + path));
        httpRequest.setHeaders(httpHeaders);
        httpRequest.execute();
    }

    public static void get(String path) throws IOException {
        HttpRequestFactory request = new NetHttpTransport().createRequestFactory();
        HttpRequest httpRequest = request.buildGetRequest(new GenericUrl(server + path));
        httpRequest.execute();
    }

    public static void post(HttpContent body, String path) throws IOException {
        HttpRequestFactory request = new NetHttpTransport().createRequestFactory();
        HttpRequest httpRequest = request.buildPostRequest(new GenericUrl(server + path), body);
        httpRequest.execute();
    }

}
