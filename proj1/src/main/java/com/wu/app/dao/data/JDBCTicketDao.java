package com.wu.app.dao.data;

import com.wu.app.dao.TicketRepository;
import com.wu.app.model.Ticket;
import com.wu.app.utils.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class JDBCTicketDao implements TicketRepository {

    private ConnectionManager cMan;
    private final Logger LOG = LogManager.getLogger("global");

    public JDBCTicketDao(ConnectionManager cMan) {
        this.cMan = cMan;
    }

    @Override
    public Ticket findByID(Integer id) {
        Ticket tikFound = null;
        Connection c = null;
        if (id != 0) {

            // never saved 'insert'
            String sql = "SELECT * from wu.ticket where id = ?";

            LOG.info("Executing statement \n " + sql);
            try {
                c = cMan.getConnection();
                PreparedStatement statement = c.prepareStatement(sql);
                statement.setInt(1, id);

                ResultSet results = statement.executeQuery();

                //parsing the resultset
                while (results.next()) {
                    int ticketID = results.getInt("id");
                    int submitID = results.getInt("submitter_id");
                    Float dollaz = results.getFloat("amount");
                    String desc = results.getString("description");
                    int revID = results.getInt("reviewer_id");
                    boolean pendStatus = results.getBoolean("is_pending") ;
                    boolean approveStatus = results.getBoolean("is_approved");


                    tikFound = new Ticket(ticketID, submitID, dollaz, desc, revID, pendStatus, approveStatus);
                }

                // turn off auto-commit
                // we want to control the transaction
                c.setAutoCommit(false);

                statement.execute();


                // everything went well commit and reset the database auto-commit flag
                c.commit();
                c.setAutoCommit(true);

                return tikFound;


            } catch (SQLException e) {
                // something went wrong try to rollback
                e.printStackTrace();
                if (c != null) {
                    try {
                        // this can fail for a number of reasons
                        // most likely the connection has been closed
                        c.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
        return null;
    }

    @Override
    public Ticket update(Ticket obj) {
        Connection c = null;
        if(obj != null && obj.getTicketID() != 0) {
            String sql = "{ call wu.update_ticket(?, ?, ?, ?, ?, ?, ?) }";

            LOG.info("Executing statement \n " + sql);
            try {
                c = cMan.getConnection();
                CallableStatement statement = c.prepareCall(sql);


                statement.setInt(1, obj.getTicketID());
                statement.setInt(2, obj.getSubmitterID());
                statement.setFloat(3, obj.getCost());
                statement.setString(4, obj.getDescription());
                statement.setInt(5, obj.getReviewerID());
                statement.setBoolean(6,obj.isPending());
                statement.setBoolean(7,obj.isApproved());

                // turn off auto-commit
                // we want to control the transaction
                c.setAutoCommit(false);

                statement.execute();

                // everything went well commit and reset the database auto-commit flag
                c.commit();
                c.setAutoCommit(true);

                return obj;


            } catch (SQLException e) {
                // something went wrong try to rollback
                e.printStackTrace();
                if(c != null) {
                    try {
                        // this can fail for a number of reasons
                        // most likely the connection has been closed
                        c.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
        return null;
    }

    @Override
    public Integer add(Ticket obj) {
        Connection c = null;
        if(obj != null) {
             String sql = "{ ? = call wu.add_new_ticket(?, ?, ?, ?, ?) }";

            LOG.info("Executing statement \n " + sql);

            try {
                Integer out_id;
                c = cMan.getConnection();
                CallableStatement statement = c.prepareCall(sql);
                statement.registerOutParameter(1, Types.INTEGER);

                statement.setInt(2, obj.getSubmitterID());
                statement.setFloat(3, obj.getCost());
                statement.setString(4, obj.getDescription());
                statement.setBoolean(5,obj.isPending());
                statement.setBoolean(6,obj.isApproved());

                // turn off auto-commit
                // we want to control the transaction
                c.setAutoCommit(false);

                statement.execute();

                out_id = (Integer) statement.getObject(1);
                // everything went well commit and reset the database auto-commit flag
                c.commit();
                c.setAutoCommit(true);

                return out_id;


            } catch (SQLException e) {
              // something went wrong try to rollback
              e.printStackTrace();
                if(c != null) {
                    try {
                        // this can fail for a number of reasons
                        // most likely the connection has been closed
                        c.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
            return null;
    }
    @Override
    public void delete(Ticket obj) {
        Connection c = null;
        if(obj.getTicketID() != 0) {
            String sql = "{ call wu.delete_ticket(?) }";

            LOG.info("Executing statement \n " + sql);

            try {
                int out_id;
                c = cMan.getConnection();
                CallableStatement statement = c.prepareCall(sql);
                statement.setInt(1, obj.getTicketID());

                // turn off auto-commit
                // we want to control the transaction
                c.setAutoCommit(false);

                statement.execute();

                // everything went well commit and reset the database auto-commit flag
                c.commit();
                c.setAutoCommit(true);
                LOG.info("Delete likely successful");
                return ;


            } catch (SQLException e) {
                // something went wrong try to rollback
                e.printStackTrace();
                if(c != null) {
                    try {
                        // this can fail for a number of reasons
                        // most likely the connection has been closed
                        c.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
        return ;
    }


    @Override
    public ArrayList<Ticket> findAllByEmployeeIDCanSort(int targetID) {
        Ticket tikFound = null;
        Connection c = null;
        ArrayList<Ticket> tix = new ArrayList<>();

        if (targetID != 0) {

            // never saved 'insert'
            String sql = "{ call wu.find_all_tickets_by_submit_id(?) }";

            LOG.info("Executing statement \n " + sql);
            try {
                c = cMan.getConnection();
                PreparedStatement statement = c.prepareCall(sql);
                statement.setInt(1, targetID);

                ResultSet results = statement.executeQuery();

                //parsing the resultset
                while (results.next()) {
                    int ticketID = results.getInt("id");
                    int submitID = results.getInt("submitter_id");
                    Float dollaz = results.getFloat("amount");
                    String desc = results.getString("description");
                    int revID = results.getInt("reviewer_id");
                    boolean pendStatus = results.getBoolean("is_pending");
                    boolean approveStatus = results.getBoolean("is_approved");


                    tikFound = new Ticket(ticketID, submitID, dollaz, desc, revID, pendStatus, approveStatus);
                    tix.add(tikFound);
                }

                // turn off auto-commit
                // we want to control the transaction
                c.setAutoCommit(false);

                statement.execute();


                // everything went well commit and reset the database auto-commit flag
                c.commit();
                c.setAutoCommit(true);

                return tix;


            } catch (SQLException e) {
                // something went wrong try to rollback
                e.printStackTrace();
                if (c != null) {
                    try {
                        // this can fail for a number of reasons
                        // most likely the connection has been closed
                        c.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
        return null;
    }

    @Override
    public ArrayList<Ticket> findAllCanSort(boolean ticketStatus) {
        Ticket tikFound = null;
        ArrayList<Ticket> tix = new ArrayList<>();
        Connection c = null;

  //      if (targetID != 0) {

            // never saved 'insert'
            String sql = "{ call wu.find_all_tickets_sort(?) }";

            LOG.info("Executing statement \n " + sql);
            try {
                c = cMan.getConnection();
                PreparedStatement statement = c.prepareCall(sql);
                statement.setBoolean(1, ticketStatus);

                ResultSet results = statement.executeQuery();

                //parsing the resultset
                while (results.next()) {
                    int ticketID = results.getInt("id");
                    int submitID = results.getInt("submitter_id");
                    Float dollaz = results.getFloat("amount");
                    String desc = results.getString("description");
                    int revID = results.getInt("reviewer_id");
                    boolean pendStatus = results.getBoolean("is_pending");
                    boolean approveStatus = results.getBoolean("is_approved");


                    tikFound = new Ticket(ticketID, submitID, dollaz, desc, revID,pendStatus, approveStatus);
                    tix.add(tikFound);
                }

                // turn off auto-commit
                // we want to control the transaction
                c.setAutoCommit(false);

                statement.execute();


                // everything went well commit and reset the database auto-commit flag
                c.commit();
                c.setAutoCommit(true);

                return tix;


            } catch (SQLException e) {
                // something went wrong try to rollback
                e.printStackTrace();
                if (c != null) {
                    try {
                        // this can fail for a number of reasons
                        // most likely the connection has been closed
                        c.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

   //     }
        return null;
    }

    @Override
    public ArrayList<Ticket> findAllPending() {
        Ticket tikFound = null;
        ArrayList<Ticket> tix = new ArrayList<>();
        Connection c = null;

        //      if (targetID != 0) {

        // never saved 'insert'
        String sql = "{ call wu.find_all_tickets_pend() }";

        LOG.info("Executing statement \n " + sql);
        try {
            c = cMan.getConnection();
            PreparedStatement statement = c.prepareCall(sql);

            ResultSet results = statement.executeQuery();

            //parsing the resultset
            while (results.next()) {
                int ticketID = results.getInt("id");
                int submitID = results.getInt("submitter_id");
                Float dollaz = results.getFloat("amount");
                String desc = results.getString("description");
                int revID = results.getInt("reviewer_id");
                boolean pendStatus = results.getBoolean("is_pending");
                boolean approveStatus = results.getBoolean("is_approved");


                tikFound = new Ticket(ticketID, submitID, dollaz, desc, revID,pendStatus, approveStatus);
                tix.add(tikFound);
            }

            // turn off auto-commit
            // we want to control the transaction
            c.setAutoCommit(false);

            statement.execute();


            // everything went well commit and reset the database auto-commit flag
            c.commit();
            c.setAutoCommit(true);

            return tix;


        } catch (SQLException e) {
            // something went wrong try to rollback
            e.printStackTrace();
            if (c != null) {
                try {
                    // this can fail for a number of reasons
                    // most likely the connection has been closed
                    c.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        //     }
        return null;
    }
}
