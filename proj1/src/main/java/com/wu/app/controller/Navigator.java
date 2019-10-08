package com.wu.app.controller;

import com.wu.app.services.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Navigator {

    public static String navigate(HttpServletRequest req, HttpServletResponse res){
        switch(req.getRequestURI()) {
            case "/proj_1_redux_war_exploded/html/Login.do":
                return LoginActions.login(req, res);
            case "/proj_1_redux_war_exploded/html/submitTicket.do":
                return EmployeeActions.submitTicket(req, res);
            case "/proj_1_redux_war_exploded/html/updateInfo.do":
                return EmployeeActions.updateInfo(req,res);
            case "/proj_1_redux_war_exploded/html/empViewTickets.do":
                return EmployeeActions.viewTickets(req, res);
            case "/proj_1_redux_war_exploded/html/managerViewAll.do":
                return ManagerActions.managerViewAllEmployee(req,res);
            case "/proj_1_redux_war_exploded/html/managerViewAllPending.do":
                return ManagerActions.viewAllPending(req,res);
            case "/proj_1_redux_war_exploded/html/managerViewAllResolved.do":
                return ManagerActions.viewAllResolved(req, res);
            case "/proj_1_redux_war_exploded/html/managerExamineEmployee.do":
                return ManagerActions.examineEmployee(req, res);
            case "/proj_1_redux_war_exploded/html/managerRegisterEmployee.do":
                return ManagerActions.RegisterEmployee(req, res);
            case "/proj_1_redux_war_exploded/html/managerUpdateTicket.do":
                return ManagerActions.updateTicket(req, res);
            default:
                System.out.println(req.getRequestURI());
                return "html/Login.html";
        }
    }

}
