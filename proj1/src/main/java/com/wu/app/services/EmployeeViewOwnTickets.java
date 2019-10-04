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
        if (yoNumba > 0) {
            ArrayList<Ticket> gottem = new ArrayList<>();
            gottem = tRepo.findAllByEmployeeIDCanSort(yoNumba);
            if (gottem.get(0) == null) {
                //Log.Info("ruh roh, bad employee number
            }
            return gottem;
        }
        return new ArrayList<Ticket>();
    }
}
