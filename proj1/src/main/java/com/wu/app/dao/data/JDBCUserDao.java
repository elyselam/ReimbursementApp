package com.wu.app.dao.data;

import com.wu.app.dao.UserRepository;
import com.wu.app.model.Ticket;
import com.wu.app.model.User;
import com.wu.app.utils.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class JDBCUserDao implements UserRepository {

    private ConnectionManager cMan;
    private final Logger LOG = LogManager.getLogger("global");
    public JDBCUserDao(ConnectionManager cMan) {
        this.cMan = cMan;
    }

    @Override
    public User findByEmail(String emailzy) {
        User uzrFound = null;
        Connection c = null;
        if (emailzy != null) {

            // never saved 'insert'
            String sql = "SELECT * from wu.users where email = ?";

            LOG.info("Executing statement \n " + sql);
            try {
                c = cMan.getConnection();
                PreparedStatement statement = c.prepareStatement(sql);
                statement.setString(1, emailzy);

                ResultSet results = statement.executeQuery();

                //parsing the resultset
                while (results.next()) {
                    int userID = results.getInt("id");
                    String firstName = results.getString("first_name");
                    String lastName = results.getString("last_name");
                    String emailz = results.getString("email");
                    String hashy = results.getString("hashPW");
                    boolean isMan = results.getBoolean("is_manager") ;


                    uzrFound = new User(userID, firstName, lastName, emailz, hashy, isMan);
                }

                // turn off auto-commit
                // we want to control the transaction
                c.setAutoCommit(false);

                statement.execute();


                // everything went well commit and reset the database auto-commit flag
                c.commit();
                c.setAutoCommit(true);

                return uzrFound;


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
    public User findByLastName(String lastName) {
        return null;
    } //not gonna use this

    @Override
    public ArrayList<User> findAll() {
    User uzrFound = null;
    ArrayList<User> userz = new ArrayList<>();
    Connection c = null;

        // never saved 'insert'
        String sql = "SELECT * from wu.users";

        LOG.info("Executing statement \n " + sql);
        try {
            c = cMan.getConnection();
            PreparedStatement statement = c.prepareStatement(sql);

            ResultSet results = statement.executeQuery();

            //parsing the resultset
            while (results.next()) {
                int userID = results.getInt("id");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String emailz = results.getString("email");
                String hashy = results.getString("hashPW");
                boolean isMan = results.getBoolean("is_manager") ;


                uzrFound = new User(userID, firstName, lastName, emailz, hashy, isMan);
                userz.add(uzrFound);
            }

            // turn off auto-commit
            // we want to control the transaction
            c.setAutoCommit(false);

            statement.execute();


            // everything went well commit and reset the database auto-commit flag
            c.commit();
            c.setAutoCommit(true);

            return userz;


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
        return null;
}

    @Override
    public User findByID(Integer id) {
        User uzrFound = null;
        Connection c = null;
        if (id != 0) {

            // never saved 'insert'
            String sql = "SELECT * from wu.users where id = ?";

            LOG.info("Executing statement \n " + sql);
            try {
                c = cMan.getConnection();
                PreparedStatement statement = c.prepareStatement(sql);
                statement.setInt(1, id);

                ResultSet results = statement.executeQuery();

                //parsing the resultset
                while (results.next()) {
                    int userID = results.getInt("id");
                    String firstName = results.getString("first_name");
                    String lastName = results.getString("last_name");
                    String emailz = results.getString("email");
                    String hashy = results.getString("hashPW");
                    boolean isMan = results.getBoolean("is_manager") ;


                    uzrFound = new User(userID, firstName, lastName, emailz, hashy, isMan);
                }

                // turn off auto-commit
                // we want to control the transaction
                c.setAutoCommit(false);

                statement.execute();


                // everything went well commit and reset the database auto-commit flag
                c.commit();
                c.setAutoCommit(true);

                return uzrFound;


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
    public User update(User obj) {
        Connection c = null; //IMPLEMENT BCRYPT FOR PASSWORD
        if(obj != null) {
            String sql = "{ ? = call wu.update_user( ?, ?, ?, ?, ?) }";

            LOG.info("Executing statement \n " + sql);

            try {
                int out_id;
                c = cMan.getConnection();
                CallableStatement statement = c.prepareCall(sql);
                statement.setInt(1, obj.getEmployeeID());
                statement.setString(2, obj.getFirstName());
                statement.setString(3, obj.getLastName());
                statement.setString(4, obj.getEmail());
                statement.setString(5, BCrypt.hashpw(obj.getHashedPassword(), BCrypt.gensalt()));
                statement.setBoolean(6,obj.isManager());

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
    public Integer add(User obj) {
        Connection c = null; //Implement bcrypt for passwords
        if(obj != null) {
            String sql = "{ ? = call wu.add_new_user(?, ?, ?, ?, ?) }";

            LOG.info("Executing statement \n " + sql);

            try {
                int out_id;
                c = cMan.getConnection();
                CallableStatement statement = c.prepareCall(sql);
                statement.registerOutParameter(1, Types.INTEGER);

                statement.setString(2, obj.getFirstName());
                statement.setString(3, obj.getLastName());
                statement.setString(4, obj.getEmail());
                statement.setString(5, BCrypt.hashpw(obj.getHashedPassword(), BCrypt.gensalt()));
                statement.setBoolean(6,obj.isManager());

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
    public void delete(User obj) {
        Connection c = null;
        if(obj.getEmployeeID() != 0) {
            String sql = "{ call wu.delete_user(?) }";

            LOG.info("Executing statement \n " + sql);

            try {
                int out_id;
                c = cMan.getConnection();
                CallableStatement statement = c.prepareCall(sql);
                statement.setInt(1, obj.getEmployeeID());

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
}
