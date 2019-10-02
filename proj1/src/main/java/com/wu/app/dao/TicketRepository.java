package com.wu.app.dao;

import com.wu.app.model.Ticket;

import java.util.ArrayList;

public interface TicketRepository extends Repository<Integer, Ticket> {
    public ArrayList<Ticket> findAllByEmployeeIDCanSort(int targetID) ;
    public ArrayList<Ticket> findAllCanSort(boolean ticketStatus); //status for approved/denied
    public ArrayList<Ticket> findAllPending();
}
