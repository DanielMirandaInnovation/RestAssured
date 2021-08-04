package com.restassured.automation.models;

public class DataInjection {

    private String baseUri, basePath;

    public DataInjection(){
        this.baseUri = "https://reqres.in";
        this.basePath = "/api";
    }

    public String getBaseUri() {
        return baseUri;
    }

    public String getBasePath() {
        return basePath;
    }
}
