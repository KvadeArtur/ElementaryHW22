package com.kvart;

import java.util.List;

public interface Storage {

    int removeAll();
    int removeUser(int id);
    int removeUserByName(String name);
    void addUser(Users user);
    void updateUser(Users user);
    Users getUser(int id);
    List<Users> getAllUsers();
}
