package com.wu.app.services;

import com.wu.app.dao.UserRepository;
import com.wu.app.model.User;
import org.mindrot.jbcrypt.BCrypt;

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


        User usey = uRepo.findByEmail(authMe.getEmail());
        boolean auth = (BCrypt.checkpw(authMe.getHashedPassword(),usey.getHashedPassword()));
        if (auth) {
            return usey; //BCRYPT MIGHT CAUSE PROBLEMS
        }
        else {
            return failed;
        }
    }
}
