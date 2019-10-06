package test.services;

import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;
import com.wu.app.services.EmployeeSubmitTicket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class EmployeeSubmitTicketTest {
    private TicketRepository mockTRepo;
    private Ticket ticksy;
    private EmployeeSubmitTicket service;

    @Before
    public void init() {
        mockTRepo = Mockito.mock(TicketRepository.class);
        ticksy = new Ticket(8,1, 220f, "need diamonds to be fly",  0, true, false);
        service = new EmployeeSubmitTicket(mockTRepo);
    }

    @Test
    public void notNull(){
        Assert.assertNull("somehow not null", service.doService(null));
    }

    @Test
    public void changeTicksyID() {
        Mockito.when(mockTRepo.add(ticksy)).thenReturn(22); //MOCKITO REPLACES TALKING TO THE DAO
        service.doService(ticksy);
        Assert.assertEquals("math idk", 22, ticksy.getTicketID());
    }

}
