package com.MyApp.web;

import java.util.Objects;

public class Request {
    private String URI;
    private String method;

    public Request(String URI, String method) {
        this.URI = URI;
        this.method = method;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getURI() {
        return URI;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(URI, request.URI) &&
                Objects.equals(method, request.method);
    }

    @Override
    public int hashCode() {

        return Objects.hash(URI, method);
    }
}