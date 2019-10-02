package com.wu.app.dao;

import com.wu.app.model.Ticket;

import java.util.ArrayList;

public interface TicketRepository extends Repository<Integer, Ticket> {
    public ArrayList<Ticket> findAllByEmployeeID(int targetID, int ticketStatus) ; //1 for resolved, 2 for pending, 0 for all
    public ArrayList<Ticket> findAll(int ticketStatus); //this is clean, but likely slow
}
