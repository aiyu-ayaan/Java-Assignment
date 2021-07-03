package com.java.in.data;

import com.java.in.model.User;
import com.java.in.utils.Constants;
import com.java.in.utils.DuplicateUserFound;
import com.java.in.utils.NoUserFound;

import java.util.List;

public class UserDatabaseImp implements UserDatabase {

    private List<User> allUser;

    public UserDatabaseImp() {
//        Setting predefined user from Constants::class
        allUser = Constants.INSTANCE.getList();
    }


    public User getUser(User user) throws NoUserFound {
        for (var u : allUser) {
            if (u.getUserName().equals(user.getUserName()) &&
                    u.getUserPassword().equals(user.getUserPassword())
            ) {
                return u;
            }
        }
        throw new NoUserFound("No User found with this user name or either your password is incorrect \n");
    }

    @Override
    public User getUserByUserName(String userName) throws NoUserFound {
        for (var user : allUser) {
            if (userName.equals(user.getUserName()))
                return user;
        }
        throw new NoUserFound("No user found with this userName !!!\n");
    }

    @Override
    public List<User> getAllUsers() {
        return allUser;
    }

    @Override
    public User logIn(String userName, String password) throws NoUserFound {
        return getUser(new User(userName, password));
    }

    @Override
    public boolean addUser(User user) throws DuplicateUserFound {
        for (var u : allUser) {
            if (u.getUserName().equals(user.getUserName())) {
                throw new DuplicateUserFound("User is already register !!!\n");
            }
        }
        allUser.add(user);
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        return true;
    }

    @Override
    public boolean changePassword(User user, String password) throws NoUserFound {
        var logInUser = getUser(user);
        if (logInUser != null) {
            logInUser.setUserPassword(password);
            return true;
        }
        return false;
    }
}
