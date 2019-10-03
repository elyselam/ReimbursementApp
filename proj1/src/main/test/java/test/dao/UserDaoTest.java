package test.dao;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wu.app.dao.UserRepository;
import com.wu.app.dao.data.JDBCUserDao;
import com.wu.app.model.User;
import com.wu.app.utils.ConnectionManager;
import com.wu.app.utils.PostgresConnectionManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class UserDaoTest {
    private ConnectionManager conn;
    private Properties propz = new Properties();
    private User usey;
    private UserRepository uRepo;

    @Before
    public void init() {
        String fileName = ClassLoader
                .getSystemClassLoader().getResource("test.properties") //HARDCODED PROPERTIES FILE
                .getFile();

        try {
            this.propz.load(new FileReader(fileName));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        this.conn = new PostgresConnectionManager(this.propz);
        this.uRepo = new JDBCUserDao(conn);
        usey = new User(6, "RJ", "Sargent", "RJS@military.com", "upupdowndown", false);
    }

    @Test
    public void addRJ() {
        uRepo.add(usey);
        //LOOK WITH EYES
    }

    @Test
    public void updateRJ() {
        usey.setHashedPassword("much better password");
        uRepo.update(usey);
        //verify w/ eyes
    }

    @Test
    public void deleteRJ() {
        uRepo.delete(usey);
    }

    @Test
    public void emailTest () {
        User foundem = uRepo.findByEmail("dozl@gmail.com");
        System.out.println(foundem.getFirstName());
        Assert.assertEquals("wrong name", "David", foundem.getFirstName() );
    }

    @Test
    public void findManyUser() {
        ArrayList<User> pplz = new ArrayList<>();
        pplz = uRepo.findAll() ;
        Assert.assertEquals("oopsie", "Jessica", pplz.get(3).getFirstName());
    }

    @Test
    public void findElyseByID() {
        Assert.assertEquals("yikes", "Elyse", uRepo.findByID(1).getFirstName());
    }
}
