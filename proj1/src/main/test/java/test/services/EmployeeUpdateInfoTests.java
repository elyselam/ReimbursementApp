package test.services;

import com.wu.app.dao.TicketRepository;
import com.wu.app.dao.UserRepository;
import com.wu.app.model.Ticket;
import com.wu.app.model.User;
import com.wu.app.services.EmployeeUpdateInfo;
import com.wu.app.services.EmployeeViewOwnTickets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class EmployeeUpdateInfoTests {
    private UserRepository mockURepo;
    private User usey;
    private EmployeeUpdateInfo service;

    @Before
    public void init() {
        mockURepo = Mockito.mock(UserRepository.class);
        usey = new User(22, "David", "Biffert", "secondary@david.com", "123", false);
        service = new EmployeeUpdateInfo(mockURepo);
    }

    @Test
    public void nullUser() {
        Assert.assertNull("Somehow has a value", service.doService(null));
    }

    @Test
    public void goodUser() {
        User haram = new User(19,"kek","lol","narp","321", false);
        Mockito.when(mockURepo.update(usey)).thenReturn(haram);
        Assert.assertEquals("wrong user information", haram, service.doService(usey));
    }

    //test for not found?
}
