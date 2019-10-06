package com.wu.app.services;

import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;

public class EmployeeSubmitTicket {
    private TicketRepository tRepo;

    public EmployeeSubmitTicket(TicketRepository tRepo) {
        this.tRepo = tRepo;
    }

    public Ticket doService(Ticket tik) {
        if (tik != null) {
            int id = tRepo.add(tik) ;
            tik.setTicketID(id);
           //Log.Info("Ticket probably in database");
            return tik;
        }
        return null;
    }
}
