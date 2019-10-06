package com.wu.app.services;

import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;

import java.util.ArrayList;

public class ManagerViewAllPending {
    private TicketRepository tRepo;

    public ManagerViewAllPending(TicketRepository tRepo) {
        this.tRepo = tRepo;
    }

    public ArrayList<Ticket> doService() {
        return tRepo.findAllPending();
    }
}
