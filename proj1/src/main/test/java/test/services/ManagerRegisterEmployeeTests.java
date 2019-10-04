package test.services;

import com.wu.app.dao.UserRepository;
import com.wu.app.model.User;
import com.wu.app.services.ManagerRegisterEmployee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ManagerRegisterEmployeeTests {
    private UserRepository mockURepo;
    private User usey;
    private ManagerRegisterEmployee service;

    @Before
    public void init() {
        mockURepo = Mockito.mock(UserRepository.class);
        usey = new User(22, "David", "Biffert", "secondary@david.com", "123", false);
        service = new ManagerRegisterEmployee(mockURepo);
    }

    @Test
    public void badUser() {
        Assert.assertNull("somehow not null", service.doService(null));
    }

    @Test
    public void decentUser() {
        Mockito.when(mockURepo.add(usey)).thenReturn(16); //mockito gives Employee ID to 16
        Assert.assertEquals("idkwat", 16, service.doService(usey).getEmployeeID()); //check to see if ID rly changed
    }
}
