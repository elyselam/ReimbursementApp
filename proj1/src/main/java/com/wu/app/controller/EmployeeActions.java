//package com.wu.app.controller;
//
//import com.wu.app.dao.TicketRepository;
//import com.wu.app.model.Ticket;
//import com.wu.app.model.User;
//import com.wu.app.services.EmployeeSubmitTicket;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

//public class EmployeeActions {

//    EmployeeSubmitTicket submitter = new EmployeeSubmitTicket();
//    public static String submitTicket(HttpServletRequest req) {
//        Ticket newTicket = new Ticket();
//        User emp = (User)req.getSession().getAttribute("user");
//
//        newTicket.setApproved(false);
//        newTicket.setCost(Float.parseFloat(req.getParameter("amountInput")));
//         newTicket.setSubmitterID(emp.getEmployeeID());
//         newTicket.setDescription(req.getParameter("descriptionInput"));
//        //call submit ticket service and submit that sh*t
//    }
//    public static String updateInfo(HttpServletRequest req, HttpServletResponse res) {
//    }
//
//    public static String viewTickets(HttpServletRequest req, HttpServletResponse res) {
//    }
//}


package com.wu.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;
import com.wu.app.model.User;
import com.wu.app.services.EmployeeSubmitTicket;
import com.wu.app.services.EmployeeUpdateInfo;
import com.wu.app.services.EmployeeViewOwnTickets;
import com.wu.app.services.ManagerViewAllEmployee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeActions {
    public static String submitTicket(HttpServletRequest req, HttpServletResponse res) {
        Ticket newTicket = new Ticket();
        newTicket.setApproved(false);
        newTicket.setPending(true);
        newTicket.setCost(Float.parseFloat(req.getParameter("cost")));
        newTicket.setSubmitterID(Integer.parseInt(req.getParameter("empID")));
        newTicket.setDescription(req.getParameter("description"));

        EmployeeSubmitTicket servy = (EmployeeSubmitTicket)req.getServletContext().getAttribute("submitTicketServ");
        servy.doService(newTicket);

        return "wat";
        //HOW THE SHIT DO WE END GET REQUESTS
        //call submit ticket service and submit that sh*t
    }
    public static String updateInfo(HttpServletRequest req, HttpServletResponse res) {
        try {
            User emp = (User)req.getSession().getAttribute("user");
            EmployeeUpdateInfo servy = (EmployeeUpdateInfo) req.getServletContext().getAttribute("updateInfoServ");
            ObjectMapper obMap = (ObjectMapper) req.getServletContext().getAttribute("obMap");

            //like this for whatever fields we allow them to change
            emp.setFirstName((String)req.getAttribute("firstynamey"));

            User noo = servy.doService(emp);

            String json = obMap.writeValueAsString(noo);

            res.getWriter().write(json);
            res.setContentType("application/json");
            res.setStatus(202);

        } catch (IOException e) {
            e.printStackTrace();
            return "boo";
        }
        return "yay";
    }



    public static String viewTickets(HttpServletRequest req, HttpServletResponse res) {
        try {
            EmployeeViewOwnTickets servy = (EmployeeViewOwnTickets) req.getServletContext().getAttribute("employeeViewOwnTicketsServ");
            ArrayList<Ticket> tix = servy.doService(Integer.parseInt(req.getParameter("empID")));

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
}