package com.wu.app.controller;

import com.wu.app.services.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Navigator {

    public static String navigate(HttpServletRequest req, HttpServletResponse res){
        switch(req.getRequestURI()) {
            case "/proj_1_redux_war_exploded/tang/Login.do":
                return LoginActions.login(req, res);
            case "/proj_1_redux_war_exploded/tang/submitTicket.do":
                return EmployeeActions.submitTicket(req, res);
            case "/proj_1_redux_war_exploded/tang/updateInfo.do":
                return EmployeeActions.updateInfo(req,res);
            case "/proj_1_redux_war_exploded/tang/empViewTickets.do":
                return EmployeeActions.viewTickets(req, res);
            case "/proj_1_redux_war_exploded/tang/managerViewAll.do":
                return ManagerActions.managerViewAllEmployee(req,res);
            case "/proj_1_redux_war_exploded/tang/managerViewAllPending.do":
                return ManagerActions.viewAllPending(req,res);
            case "/proj_1_redux_war_exploded/tang/managerViewAllResolved.do":
                return ManagerActions.viewAllResolved(req, res);
            case "/proj_1_redux_war_exploded/tang/managerExamineEmployee.do":
                return ManagerActions.examineEmployee(req, res);
            case "/proj_1_redux_war_exploded/tang/managerRegisterEmployee.do":
                return ManagerActions.RegisterEmployee(req, res);
            default:
                return "/Login.html";
        }
    }

}
