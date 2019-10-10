package com.wu.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.app.model.User;
import com.wu.app.model.Ticket;
import com.wu.app.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ManagerActions {
    public static String managerViewAllEmployee(HttpServletRequest req, HttpServletResponse res) {

        try {
            ManagerViewAllEmployee servy = (ManagerViewAllEmployee) req.getServletContext().getAttribute("managerViewAllEmployeeServ");
            ArrayList<User> empz = servy.doService();
            empz.forEach(n -> n.setHashedPassword("nope"));

            ObjectMapper obMap = (ObjectMapper) req.getServletContext().getAttribute("obMap");

            String json = obMap.writeValueAsString(empz);

            res.getWriter().write(json);
            res.setContentType("application/json");
            res.setStatus(202);
        } catch (IOException e) {
            e.printStackTrace();
            return "boo";
        }
        return "yay";
    }

    public static String viewAllPending(HttpServletRequest req, HttpServletResponse res) {
        try {
            ManagerViewAllPending servy = (ManagerViewAllPending) req.getServletContext().getAttribute("managerViewAllPendingServ");
            ArrayList<Ticket> tix = servy.doService();

            ObjectMapper obMap = (ObjectMapper) req.getServletContext().getAttribute("obMap");

            String json = obMap.writeValueAsString(tix);

            res.getWriter().write(json);
            res.setContentType("application/json");
            res.setStatus(202);
        } catch (IOException e) {
            e.printStackTrace();
            return "boo";
        }
        return "yay";
    }

    public static String viewAllResolved(HttpServletRequest req, HttpServletResponse res) {
        try {
            ManagerViewAllResolved servy = (ManagerViewAllResolved) req.getServletContext().getAttribute("managerViewAllResolvedServ");
            ArrayList<Ticket> tix = servy.doService();

            ObjectMapper obMap = (ObjectMapper) req.getServletContext().getAttribute("obMap");

            String json = obMap.writeValueAsString(tix);

            res.getWriter().write(json);
            res.setContentType("application/json");
            res.setStatus(202);
        } catch (IOException e) {
            e.printStackTrace();
            return "boo";
        }
        return "yay";
    }

    public static String examineEmployee(HttpServletRequest req, HttpServletResponse res) {
        try {
            ManagerExamineEmployee servy = (ManagerExamineEmployee) req.getServletContext().getAttribute("managerExamineEmployeeServ");
            ArrayList<Ticket> tix = servy.doService((int)req.getAttribute("targetID"));

            ObjectMapper obMap = (ObjectMapper) req.getServletContext().getAttribute("obMap");

            String json = obMap.writeValueAsString(tix);

            res.getWriter().write(json);
            res.setContentType("application/json");
            res.setStatus(202);
        } catch (IOException e) {
            e.printStackTrace();
            return "boo";
        }
        return "yay";
    }

    public static String RegisterEmployee(HttpServletRequest req, HttpServletResponse res) {
//        try {
//            ManagerViewAllEmployee servy = (ManagerViewAllEmployee) req.getServletContext().getAttribute("managerViewAllEmployeeServ");
//        User emp;
//
//        //like this for whatever fields we allow them to change
//        //emp.setFirstName((String)req.getAttribute("firstynamey"));
//
//        //User noo = servy.doService(emp);
//
//
//    } catch (IOException e) {
//        e.printStackTrace();
//        return "boo";
//    }
        return "yay";
    } //THIS MIGHT NOT BE HOW WE ARE SUPPOSED TO END A GET. I DON'T KNOW LOL.

    public static String updateTicket(HttpServletRequest req, HttpServletResponse res) {

        ManagerUpdateTicket servy = (ManagerUpdateTicket) req.getServletContext().getAttribute("managerUpdateTicketServ");
        ObjectMapper obMap = (ObjectMapper) req.getServletContext().getAttribute("obMap");

        Ticket tix = null;
        try {
            tix = obMap.readValue(req.getInputStream(), Ticket.class);
        } catch (IOException e) {
            e.printStackTrace();
            return "oopsie" ;
        }

        servy.doService(tix);

        try {
            res.getWriter().write("orangutan");
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.setStatus(204);

        return "wat";
    }
}
