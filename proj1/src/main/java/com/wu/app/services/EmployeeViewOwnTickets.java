package com.wu.app.services;

import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;

import java.util.ArrayList;

public class EmployeeViewOwnTickets {
    private TicketRepository tRepo;

    public EmployeeViewOwnTickets(TicketRepository tRepo) {
        this.tRepo = tRepo;
    }

    public ArrayList<Ticket> doService(int yoNumba) {
        if (yoNumba >= 0) {

        }
        return new ArrayList<Ticket>();
    }
}
