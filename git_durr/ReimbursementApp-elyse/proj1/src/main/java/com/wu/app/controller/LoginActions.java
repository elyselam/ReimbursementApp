package com.wu.app.controller;

import com.wu.app.dao.UserRepository;
import com.wu.app.dao.data.JDBCUserDao;
import com.wu.app.model.User;
import com.wu.app.services.LoginService;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;

public class LoginActions {
//    static Logger logger = Logger.getLogger(LoginActions.class);


    public static String login(HttpServletRequest request) {
        LoginService loginServ = (LoginService) request.getServletContext().getAttribute( "loginServiceServ");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //if person exists & password is correct
        //retrieve user from DB set it equal to a user OBJ
        User u = new User(0, "Hotmoves", "Ghandi", email, password, false);
        //findEmployeeByID/Username returns user
        User fullUser = loginServ.doService(u);
        //set the user as a session attribute
        if (fullUser.getEmployeeID() == -2 || fullUser == null) {
            return "/Login.html";
        } else if (fullUser.isManager()) {
            request.getSession().setAttribute("user", fullUser);
            return "/Manager.html";
        } else {
            request.getSession().setAttribute("user", fullUser);
            return "/Employee.html";
        }


    }
}

