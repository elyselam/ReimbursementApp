package com.wu.app.services;

import com.wu.app.dao.UserRepository;
import com.wu.app.model.User;

public class ManagerRegisterEmployee {
    private UserRepository uRepo;

    public ManagerRegisterEmployee(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public User doService(User boyo) { // someday implent bcrypt\

        if (boyo != null) {
            int ido =uRepo.add(boyo); //get new EmployeeID from database
            boyo.setEmployeeID(ido); //update Employee ID in local object
            return boyo;
        }
        return null;
    }

}
