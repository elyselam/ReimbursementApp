package test.services;

import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;
import com.wu.app.services.EmployeeSubmitTicket;
import com.wu.app.services.ManagerExamineEmployee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class ManagerExamineEmployeeTests {
    private TicketRepository mockTRepo;
    private Ticket ticksy;
    private ManagerExamineEmployee service;
    private ArrayList<Ticket> tixy;

    @Before
    public void init() {
        mockTRepo = Mockito.mock(TicketRepository.class);
        ticksy = new Ticket(8,1, 220f, "need diamonds to be fly",  0, true, false);
        service = new ManagerExamineEmployee(mockTRepo);
        tixy = new ArrayList<>();
    }

    @Test
    public void zeroID() {
        Assert.assertNull("yikes",service.doService(0) );
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
