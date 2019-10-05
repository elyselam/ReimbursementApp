package com.wu.app.controller;

import com.wu.app.dao.UserRepository;
import com.wu.app.dao.data.JDBCUserDao;
import com.wu.app.services.LoginService;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.UserDataHandler;

import javax.servlet.http.HttpServletRequest;

public class LoginActions {
    static Logger logger = Logger.getLogger(LoginController.class);

    public static String Login(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");







}

    public static String login(HttpServletRequest req) {
    }
