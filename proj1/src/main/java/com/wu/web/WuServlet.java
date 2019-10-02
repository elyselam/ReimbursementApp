package com.wu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WuServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Calling Service");

        if(req.getMethod().equals("GET")) {
            //this just sends a plain text response
            resp.getWriter().write("Happy Birthday");
            resp.setContentType("text/plain");
            resp.setStatus(200);
        } else if (req.getMethod().equals("POST")) {
            resp.getWriter().write("Trying my best");
            resp.setContentType("text/plain");
            resp.setStatus(200);
        }
    }

    @Override
    public void destroy() {
        System.out.println("Calling Destroy");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Calling init");
        super.init();
    }
}
