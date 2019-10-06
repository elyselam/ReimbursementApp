package test.services;

import com.wu.app.dao.UserRepository;
import com.wu.app.model.User;
import com.wu.app.services.ManagerRegisterEmployee;
import com.wu.app.services.ManagerViewAllEmployee;
import org.junit.Before;
import org.mockito.Mockito;

public class ManagerViewAllEmployeeTest {
    private UserRepository mockURepo;
    private User usey;
    private ManagerViewAllEmployee service;

    @Before
    public void init() {
        mockURepo = Mockito.mock(UserRepository.class);
        usey = new User(22, "David", "Biffert", "secondary@david.com", "123", false);
        service = new ManagerViewAllEmployee(mockURepo);
    }

    //there really isn't much that can go wrong here...
}
