package com.watanya.archive.data.interfaces;


import com.watanya.archive.data.model.User;

import java.util.List;

public interface DbInterface {

    void getAllUsers(OperationListener<List<User>> listener);
    void insertUser(final User user,OperationListener<Void> listener);
    void getLoggedInUser(OperationListener<User> listener);
    void deleteLoggedInUser(OperationListener<Void> listener);

}
