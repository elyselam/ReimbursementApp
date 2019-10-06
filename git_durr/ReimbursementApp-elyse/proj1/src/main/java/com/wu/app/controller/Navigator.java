package com.wu.app.controller;

import com.wu.app.services.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Navigator {

    public static String navigate(HttpServletRequest req, HttpServletResponse res){
        switch(req.getRequestURI()) {
            case "/Login.do":
                return LoginActions.login(req);
            case "/submitTicket.do":
                return EmployeeActions.submitTicket(req);
            case "/updateInfo.do":
                return EmployeeActions.updateInfo(req,res);
            case "/empViewTickets.do":
                return EmployeeActions.viewTickets(req, res);
            case "/managerViewAllEmployee.do":
                return ManagerActions.managerViewAllEmployee(req,res);
            case "/managerViewAllPending.do":
                return ManagerActions.viewAllPending(req,res);
            case "/managerViewAllResolved.do":
                return ManagerActions.viewAllResolved(req, res);
            case "/managerExamineEmployee.do":
                return ManagerActions.examineEmployee(req, res);
            case "/managerRegisterEmployee.do":
                return ManagerActions.RegisterEmployee(req);
            default:
                return "/Login.html";
        }
    }

}
