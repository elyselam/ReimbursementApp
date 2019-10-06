package com.wu.app.dao.data;

import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;
import com.wu.app.utils.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class JDBCTicketDao implements TicketRepository {

    private ConnectionManager cMan;
    private final Logger LOG = LogManager.getLogger("global");

    public JDBCTicketDao(ConnectionManager cMan) {
        this.cMan = cMan;
    }

    @Override
    public Ticket findByID(Integer id) {
        return null;
    }

    @Override
    public Ticket update(Ticket obj) {
        return null;
    }

    @Override
    public Integer add(Ticket obj) {
        return null;
    }

    @Override
    public void delete(Ticket obj) {

    }

    @Override
    public ArrayList<Ticket> findAllByEmployeeID(int targetID, int ticketStatus) {
        return null;
    }

    @Override
    public ArrayList<Ticket> findAll(int ticketStatus) {
        return null;
    }
}
