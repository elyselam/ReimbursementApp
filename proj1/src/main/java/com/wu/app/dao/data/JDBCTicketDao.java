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
//            Statement statement = c.createStatement();
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
                    boolean approveStatus = results.getBoolean("is_approved");


                    tikFound = new Ticket(ticketID, submitID, dollaz, desc, revID, approveStatus);
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
            String sql = "{ call wu.update_ticket(?, ?, ?, ?, ?, ?) }";

            LOG.info("Executing statement \n " + sql);
            try {
                c = cMan.getConnection();
                CallableStatement statement = c.prepareCall(sql);


                statement.setInt(1, obj.getTicketID());
                statement.setInt(2, obj.getSubmitterID());
                statement.setFloat(3, obj.getCost());
                statement.setString(4, obj.getDescription());
                statement.setInt(5, obj.getReviewerID());
                statement.setBoolean(6,obj.isApproved());

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
        if(obj != null && obj.getTicketID() != 0) {
             String sql = "{ ? call wu.add_new_ticket(?, ?, ?, ?, ?) }";

            LOG.info("Executing statement \n " + sql);

            try {
                int out_id;
                c = cMan.getConnection();
                CallableStatement statement = c.prepareCall(sql);
                statement.registerOutParameter(1, Types.INTEGER);

                statement.setInt(2, obj.getSubmitterID());
                statement.setFloat(3, obj.getCost());
                statement.setString(4, obj.getDescription());
                statement.setInt(5, obj.getReviewerID());
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
    public ArrayList<Ticket> findAllByEmployeeIDCanSort(int targetID, int ticketStatus) {
        Ticket tikFound = null;
        ArrayList<Ticket> tix;
        boolean yep;
        Connection c = null;
        switch (ticketStatus) {
            case 0:
                yep = new boolean;
                break; //when we want to keep it null to search for pending
            case 1:
                yep = true;
                break; //search for approved
            case 2:
                yep = false;
                break; //search for denied
            default:
                LOG.info("Unexpected ticketStatus");
                return null;
                break;
        }


        if (targetID != 0) {

            // never saved 'insert'
            String sql = "{ call wu.find_all_tickets_by_submit_id(?, ?) } = ?";

            LOG.info("Executing statement \n " + sql);
            try {
                c = cMan.getConnection();
//            Statement statement = c.createStatement();
                PreparedStatement statement = c.prepareStatement(sql);
                statement.setInt(1, targetID);
                statement.setBoolean(2, yep);

                ResultSet results = statement.executeQuery();

                //parsing the resultset
                while (results.next()) {
                    int ticketID = results.getInt("id");
                    int submitID = results.getInt("submitter_id");
                    Float dollaz = results.getFloat("amount");
                    String desc = results.getString("description");
                    int revID = results.getInt("reviewer_id");
                    boolean approveStatus = results.getBoolean("is_approved");


                    tikFound = new Ticket(ticketID, submitID, dollaz, desc, revID, approveStatus);
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
    public ArrayList<Ticket> findAllCanSort(int ticketStatus) {
        Ticket tikFound = null;
        ArrayList<Ticket> tix = new ArrayList<>();
        boolean yep;
        Connection c = null;
        switch (ticketStatus) {
            case 0:
                break; //when we want to keep it null to search for pending
            case 1:
                yep = true;
                break; //search for approved
            case 2:
                yep = false;
                break; //search for denied
            default:
                LOG.info("Unexpected ticketStatus");
                return null;
            break;
        }


  //      if (targetID != 0) {

            // never saved 'insert'
            String sql = "{ call wu.find_all_tickets( ?) } = ?";

            LOG.info("Executing statement \n " + sql);
            try {
                c = cMan.getConnection();
//            Statement statement = c.createStatement();
                PreparedStatement statement = c.prepareStatement(sql);
                statement.setBoolean(1, yep);

                ResultSet results = statement.executeQuery();

                //parsing the resultset
                while (results.next()) {
                    int ticketID = results.getInt("id");
                    int submitID = results.getInt("submitter_id");
                    Float dollaz = results.getFloat("amount");
                    String desc = results.getString("description");
                    int revID = results.getInt("reviewer_id");
                    boolean approveStatus = results.getBoolean("is_approved");


                    tikFound = new Ticket(ticketID, submitID, dollaz, desc, revID, approveStatus);
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
