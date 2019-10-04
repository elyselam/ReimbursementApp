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
            return uRepo.update(nuInfo);
        }
        return null;
    }
}
