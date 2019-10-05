package com.wu.app.controller;

import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;
import com.wu.app.model.User;
import com.wu.app.services.EmployeeSubmitTicket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeActions {

//    EmployeeSubmitTicket submitter = new EmployeeSubmitTicket();
    public static String submitTicket(HttpServletRequest req) {
        Ticket newTicket = new Ticket();
        User emp = (User)req.getSession().getAttribute("user");

        newTicket.setApproved(false);
        newTicket.setCost(Float.parseFloat(req.getParameter("amountInput")));
         newTicket.setSubmitterID(emp.getEmployeeID());
         newTicket.setDescription(req.getParameter("descriptionInput"));
        //call submit ticket service and submit that sh*t
    }
    public static String updateInfo(HttpServletRequest req, HttpServletResponse res) {
    }

    public static String viewTickets(HttpServletRequest req, HttpServletResponse res) {
    }
}
