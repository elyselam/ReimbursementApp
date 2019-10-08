package com.wu.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.app.dao.TicketRepository;
import com.wu.app.dao.UserRepository;
import com.wu.app.dao.data.JDBCTicketDao;
import com.wu.app.dao.data.JDBCUserDao;
import com.wu.app.services.*;
import com.wu.app.utils.ConnectionManager;
import com.wu.app.utils.PostgresConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ContextLoader implements ServletContextListener {

        @Override
        public void contextInitialized(ServletContextEvent servletContextEvent) {
            ServletContext sc = servletContextEvent.getServletContext();
            String appName = sc.getInitParameter("ApplicationName");
            System.out.println("Web Context is being initialized for " + appName);
            System.out.println("Initializing the Connection Manager for Postgres Database");

            Properties propz = new Properties();

            propz.setProperty("SQL_DAO_URL","jdbc:postgresql://dongler.cpwa0rht5vnt.us-east-1.rds.amazonaws.com:5432/dingledongle");
            propz.setProperty("SQL_DAO_USERNAME","dozl");
            propz.setProperty("SQL_DAO_PASSWORD","hungryhippos");

            ConnectionManager manager = new PostgresConnectionManager(propz);


            UserRepository userRepo= new JDBCUserDao(manager);
            TicketRepository ticketRepo = new JDBCTicketDao(manager);

            EmployeeSubmitTicket submitTicket = new EmployeeSubmitTicket(ticketRepo);
            EmployeeUpdateInfo updateInfo  = new EmployeeUpdateInfo(userRepo);
            EmployeeViewOwnTickets employeeViewOwnTickets =
                    new EmployeeViewOwnTickets(ticketRepo);

            LoginService loginService = new LoginService(userRepo);
            ManagerExamineEmployee managerExamineEmployee =
                    new ManagerExamineEmployee(ticketRepo);
            ManagerRegisterEmployee managerRegisterEmployee =
                    new ManagerRegisterEmployee(userRepo);

            ManagerViewAllEmployee managerViewAllEmployee =
                    new ManagerViewAllEmployee(userRepo);

            ManagerViewAllPending managerViewAllPending =
                    new ManagerViewAllPending(ticketRepo);
            ManagerViewAllResolved managerViewAllResolved =
                    new ManagerViewAllResolved(ticketRepo);
            ManagerUpdateTicket managerUpdateTicket = new ManagerUpdateTicket(ticketRepo);

            ObjectMapper obMap = new ObjectMapper();

            sc.setAttribute("submitTicketServ", submitTicket);
            sc.setAttribute("updateInfoServ", updateInfo);
            sc.setAttribute("employeeViewOwnTicketsServ", employeeViewOwnTickets);
            sc.setAttribute("loginServiceServ",loginService);
            sc.setAttribute("managerRegisterEmployeeServ", managerRegisterEmployee);
            sc.setAttribute("managerExamineEmployeeServ", managerExamineEmployee);
            sc.setAttribute("managerViewAllEmployeeServ", managerViewAllEmployee);
            sc.setAttribute("managerViewAllPendingServ", managerViewAllPending);
            sc.setAttribute("managerViewAllResolvedServ", managerViewAllResolved);
            sc.setAttribute("managerUpdateTicketServ", managerUpdateTicket);
            sc.setAttribute("obMap", obMap);

        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContextEvent) {

        }
}
