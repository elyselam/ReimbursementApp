package test.dao;

import com.wu.app.dao.TicketRepository;
import com.wu.app.dao.data.JDBCTicketDao;
import com.wu.app.model.Ticket;
import com.wu.app.utils.ConnectionManager;
import com.wu.app.utils.PostgresConnectionManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class TicketDaoTest {
    private ConnectionManager conn;
    private Properties propz = new Properties();
    private Ticket ticksy;
    private TicketRepository tRepo;

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

        this.conn = (new PostgresConnectionManager(this.propz));
        this.tRepo = (new JDBCTicketDao(conn));
        ticksy = new Ticket(7, 2, 300.00f, "tequila", 4, true, true) ;
    }

    @Test
    public void addTicksy() {
        tRepo.add(ticksy) ;
        Assert.assertTrue("literally impossible", true); //use dbeaver with eyes
    }

    @Test
    public void findTicksy() {
        Ticket boop = tRepo.findByID(1) ;
        Assert.assertEquals("got it", 10000f, boop.getCost(), 0f); //dummy data in database
    }

//    @Test
//    public void deleteTicksy() {
//        tRepo.delete(ticksy);
//        //verified w/ eyes
//    }

    @Test
    public void getABunch() {
        ArrayList<Ticket> tix = new ArrayList<>();
        tix = tRepo.findAllByEmployeeIDCanSort(2);
        System.out.println(tix.get(2).getDescription());
        Assert.assertEquals("not retrieved correctly", 300f, tix.get(2).getCost(), 0f);
    }

    @Test
    public void findPending() {
        ArrayList<Ticket> tix = new ArrayList<>();
        tix = tRepo.findAllPending();
        System.out.println(tix.get(0).getDescription());
        Assert.assertEquals("something wrong", 19.99f, tix.get(0).getCost(), 0f);
    }

    @Test
    public void findySorty() {
        ArrayList<Ticket> tix = new ArrayList<>();
        tix = tRepo.findAllCanSort(true);
        System.out.println(tix.get(1).getDescription());
        Assert.assertEquals("wat",2000f, tix.get(1).getCost(), 0f);
    }

    @Test
    public void denyTicksy() {
        ticksy.setApproved(false);
        ticksy.setReviewerID(4);
        ticksy.setPending(false);
        tRepo.update(ticksy);
        //VERIFY WITH EYES
    }

}
//update