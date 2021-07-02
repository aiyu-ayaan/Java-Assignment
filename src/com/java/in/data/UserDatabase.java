package com.java.in.data;

import com.java.in.model.User;
import com.java.in.utils.DuplicateUserFound;
import com.java.in.utils.NoUserFound;

public interface UserDatabase {

    User logIn(String userName, String password)throws NoUserFound;

    boolean addUser(User user) throws DuplicateUserFound;

    boolean deleteUser(User user);
}
