package com.jaisgroup.dnd.client;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SearchClient implements RestClient {


    @Override
    public RequestHeadersUriSpec<?> get() {
        return null;
    }

    @Override
    public RequestHeadersUriSpec<?> head() {
        return null;
    }

    @Override
    public RequestBodyUriSpec post() {
        return null;
    }

    @Override
    public RequestBodyUriSpec put() {
        return null;
    }

    @Override
    public RequestBodyUriSpec patch() {
        return null;
    }

    @Override
    public RequestHeadersUriSpec<?> delete() {
        return null;
    }

    @Override
    public RequestHeadersUriSpec<?> options() {
        return null;
    }

    @Override
    public RequestBodyUriSpec method(HttpMethod method) {
        return null;
    }

    @Override
    public Builder mutate() {
        return null;
    }
}
