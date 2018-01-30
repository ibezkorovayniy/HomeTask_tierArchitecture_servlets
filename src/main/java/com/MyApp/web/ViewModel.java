package com.MyApp.web;

import java.util.HashMap;
import java.util.Map;

public class ViewModel {

    private final String PREFIX = "/WEB-INF/";
    private final String SUFFIX = ".jsp";

    private String view;
    private Map<String, Object> argumentsMap = new HashMap<>();

    public String getView() {
        return String.format("%s%s%s", PREFIX, view, SUFFIX);
    }

    public void setView(String view) {
        this.view = view;
    }

    public ViewModel(String view) {
        this.view = view;
    }

    public Map<String, Object> getArgumentsMap() {
        return argumentsMap;
    }

    public void setArgument(String argName, Object arg) {
        this.argumentsMap.put(argName, arg);
    }

    public ViewModel(String view, Map<String, Object> argumentsMap) {

        this.view = view;
        this.argumentsMap = argumentsMap;
    }

}