package com.wu.app.services;

import com.wu.app.dao.UserRepository;
import com.wu.app.model.User;

public class EmployeeUpdateInfo {
    private UserRepository uRepo;

    public EmployeeUpdateInfo(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public User doService(User nuInfo) {
        if (nuInfo != null) {
            System.out.println(nuInfo.getEmployeeID());
            User mrMan = uRepo.findByID(nuInfo.getEmployeeID());
            mrMan.setFirstName(nuInfo.getFirstName());
            mrMan.setLastName(nuInfo.getLastName());
            mrMan.setHashedPassword(nuInfo.getHashedPassword());
            return uRepo.update(mrMan);
        }
        return null;
    }
}
