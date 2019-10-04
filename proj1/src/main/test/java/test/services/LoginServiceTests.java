package test.services;

import com.wu.app.dao.UserRepository;
import com.wu.app.model.User;
import com.wu.app.services.LoginService;
import com.wu.app.services.ManagerViewAllEmployee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LoginServiceTests {
    private UserRepository mockURepo;
    private User usey;
    private LoginService service;

    @Before
    public void init() {
        mockURepo = Mockito.mock(UserRepository.class);
        usey = new User(22, "David", "Biffert", "secondary@david.com", "123", false);
        service = new LoginService(mockURepo);
    }

    @Test
    public void badFName() {
        usey.setFirstName(null);
        Assert.assertEquals("so sad", "This", service.doService(usey).getFirstName());
    }

    @Test
    public void badLName() {
        usey.setLastName(null);
        Assert.assertEquals("so sad", "Didn't", service.doService(usey).getLastName());
    }

    @Test
    public void badEmail() {
        usey.setEmail(null);
        Assert.assertEquals("so sad", "really@work", service.doService(usey).getEmail());
    }

    @Test
    public void badPW() {
        usey.setEmail(null);
        Assert.assertEquals("so sad", "kek", service.doService(usey).getHashedPassword());
    }

    @Test
    public void userNotFound() {
        User boko = new User(1,"kek","kek","kek","kek",false);
        Mockito.when(mockURepo.findByID(22)).thenReturn(boko);
        Assert.assertNull("interesting", service.doService(usey));
    }

    @Test
    public void userFound() {
        Mockito.when(mockURepo.findByID(22)).thenReturn(usey);
        Assert.assertEquals("interesting", usey, service.doService(usey));
    }
}
