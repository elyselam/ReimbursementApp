package com.wu.app.services;

import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;

public class ManagerUpdateTicket {
    private TicketRepository tRepo;

    public ManagerUpdateTicket(TicketRepository tRepo) {
        this.tRepo = tRepo;
    }

    public Ticket doService(Ticket tik) {
        if (tik != null) {
            Ticket tok = tRepo.update(tik) ;
            //Log.Info("Ticket probably in database");
            System.out.println(tok.getReviewerID());
            System.out.println(tok.isApproved());
            System.out.println(tok.getDescription());

            return tok;
        }
        return null;
    }
}
