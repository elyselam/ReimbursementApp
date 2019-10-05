package com.wu.app.controller;

import com.wu.app.dao.UserRepository;
import com.wu.app.dao.data.JDBCUserDao;
import com.wu.app.model.User;
import com.wu.app.services.LoginService;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.UserDataHandler;

import javax.servlet.http.HttpServletRequest;

public class LoginActions {
    static Logger logger = Logger.getLogger(LoginController.class);

    public static String Login(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //if person exists & password is correct
        //retrieve user from DB set it equal to a user OBJ
        User u; //findEmployeeByID/Username returns user

        //set the user as a session attribute
        request.getSession().setAttribute("user", u);






}

    public static String login(HttpServletRequest req) {
    }
