package com.wu.app.dao;

import com.wu.app.model.User;

import java.util.ArrayList;

public interface UserRepository extends Repository<Integer, User>{
    public User findByEmail(String emailz);
    public User findByLastName(String lastName);
    public ArrayList<User> findAll();
}
