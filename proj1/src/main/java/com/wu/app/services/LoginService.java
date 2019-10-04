package com.wu.app.services;

import com.wu.app.dao.UserRepository;
import com.wu.app.model.User;

public class LoginService {
    private UserRepository uRepo;

    public LoginService(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    public User doService(User authMe) {
        User failed = new User(-2, "This","Didn't", "really@work","kek",false);

        //check for nulls
        if (authMe.getFirstName() == null) {return failed;}
        if (authMe.getLastName() == null) {return failed;}
        if (authMe.getEmail() == null) {return failed;}
        if (authMe.getHashedPassword() == null) {return failed;}

        User usey = uRepo.findByID(authMe.getEmployeeID());
        if (usey.equals(authMe)) { return usey;} //BCRYPT MIGHT CAUSE PROBLEMS

        return null;
    }
}
