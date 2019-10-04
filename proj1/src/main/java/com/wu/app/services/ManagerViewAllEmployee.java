package com.wu.app.services;

import com.wu.app.dao.UserRepository;
import com.wu.app.model.User;

import java.util.ArrayList;

public class ManagerViewAllEmployee {
    UserRepository uRepo;

    public ManagerViewAllEmployee(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public ArrayList<User> doService() {
        return uRepo.findAll();
    }
}
