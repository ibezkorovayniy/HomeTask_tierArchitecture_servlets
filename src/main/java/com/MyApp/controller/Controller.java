package com.MyApp.controller;

import com.MyApp.web.ViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

    ViewModel process(HttpServletRequest request, HttpServletResponse responce);
}
