package com.wu.app.services;

import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;

import java.util.ArrayList;

public class ManagerViewAllResolved {
    private TicketRepository tRepo;

    public ManagerViewAllResolved(TicketRepository tRepo) {
        this.tRepo = tRepo;
    }

    public ArrayList<Ticket> doService() {
        ArrayList<Ticket> approvey = tRepo.findAllCanSort(true);
        ArrayList<Ticket> denyey = tRepo.findAllCanSort(false);
        approvey.addAll(denyey); //this is gross
        return approvey;
    }
}
