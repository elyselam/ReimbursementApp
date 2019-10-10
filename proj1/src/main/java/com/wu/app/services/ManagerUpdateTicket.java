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
            Ticket tok = tRepo.findByID(tik.getTicketID());
            //Log.Info("Ticket probably in database");
            tok.setReviewerID(tik.getReviewerID());
            tok.setApproved(tik.isApproved());
            tok.setPending(false);
            tRepo.update(tok) ;
            return tok;
        }


        
        return null;
    }
}
