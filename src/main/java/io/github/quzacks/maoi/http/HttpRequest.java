package io.github.quzacks.maoi.http;

import io.github.quzacks.maoi.DiscordClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Simple wrapper for easy HTTP calls.
 */
public class HttpRequest {
    private final static String BASE_API_URL = "https://discord.com/api/v10/";

    /**
     * Endpoint path.
     */
    private final String path;
    /**
     * HTTP request type.
     *
     * @see HttpRequestType
     */
    private HttpRequestType type;
    /**
     * JSON data to post.
     */
    private JSONObject json;

    /**
     * @param path Endpoint path.
     */
    public HttpRequest(String path) {
        this.path = path;
    }

    /**
     * Sets the request type.
     *
     * @param type Request type.
     * @return Modified instance of class.
     *
     * @see HttpRequestType
     */
    public HttpRequest requestType(HttpRequestType type) {
        this.type = type;
        return this;
    }

    /**
     * JSON data to POST.
     *
     * @param json JSON object.
     * @return Modified instance of class.
     */
    public HttpRequest withJson(JSONObject json) {
        this.json = json;
        return this;
    }

    public void execute(DiscordClient discordClient) {
        final CloseableHttpClient client = HttpClients.createDefault();

        switch (type) {
            case POST -> {
                final StringEntity entity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
                System.out.println(json.toString(2));
                final HttpPost post = new HttpPost(BASE_API_URL + path);
                post.setEntity(entity);

                post.setHeader("Authorization", "Bot " + discordClient.getToken());
                try {
                    final HttpResponse response = client.execute(post);
                    System.out.println(response.getStatusLine().getStatusCode());

                    assert response.getStatusLine().getStatusCode() == 200;
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            case GET -> {}
        }

        try {
            client.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
