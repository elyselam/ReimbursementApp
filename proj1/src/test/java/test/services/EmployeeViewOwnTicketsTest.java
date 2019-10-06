package test.services;

import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;
import com.wu.app.services.EmployeeViewOwnTickets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class EmployeeViewOwnTicketsTest {
    private TicketRepository mockTRepo;
    private Ticket ticksy;
    private EmployeeViewOwnTickets service;

    @Before
    public void init() {
        mockTRepo = Mockito.mock(TicketRepository.class);
        ticksy = new Ticket(8,1, 220f, "need diamonds to be fly",  0, true, false);
        service = new EmployeeViewOwnTickets(mockTRepo);
    }

    @Test
    public void badInput() {
        ArrayList<Ticket> arrly= new ArrayList<>();
        ArrayList<Ticket> baddy= service.doService(0);
        Assert.assertEquals("Somehow has values", arrly, baddy);
    }

    @Test
    public void gotStuff() {
        ArrayList<Ticket> arrly= new ArrayList<>();
        arrly.add(ticksy);
        Mockito.when(mockTRepo.findAllByEmployeeIDCanSort(8)).thenReturn(arrly);
        ArrayList<Ticket> baddy= service.doService(8);
        Assert.assertEquals("idk", arrly, baddy);

    }
}
