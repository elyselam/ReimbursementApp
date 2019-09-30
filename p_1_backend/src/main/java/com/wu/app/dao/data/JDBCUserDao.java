package com.wu.app.dao.data;

import com.wu.app.dao.UserRepository;
import com.wu.app.model.User;
import com.wu.app.utils.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCUserDao implements UserRepository {

    private ConnectionManager cMan;
    private final Logger LOG = LogManager.getLogger("global");
    public JDBCUserDao(ConnectionManager cMan) {
        this.cMan = cMan;
    }

    @Override
    public User findByEmail(String emailz) {
        return null;
    }

    @Override
    public User findByLastName(String lastName) {
        return null;
    }

    @Override
    public User findByID(Integer id) {
        return null;
    }

    @Override
    public User update(User obj) {
        return null;
    }

    @Override
    public Integer add(User obj) {
        return null;
    }

    @Override
    public void delete(User obj) {

    }
}
